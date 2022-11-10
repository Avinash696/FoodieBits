package com.example.zepto

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.test.FileUtil
import com.example.test.AviInterface
import com.example.zepto.Admin.ui.activity.AdminHomeActivity
import com.example.zepto.adapter.recyclerAdapterCategories
import com.example.zepto.databinding.FragmentCategroiesBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.CategoryImg
import com.example.zepto.model.mainCategoryModel
import com.example.zepto.module.Toasty
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


class  CategroiesFragment : Fragment() {
    private lateinit var binding: FragmentCategroiesBinding
    private lateinit var refreshCatAdmin:SwipeRefreshLayout
    private lateinit var adapter : recyclerAdapterCategories
    private var param1: String? = null
    private var param2: String? = null
    val PICK_IMAGE = 111
    val categoryStatus = 1
    private var filePath: File? = null
    lateinit var myDataFromActivity: String
    private val repo = RetrofitHelper.getClient().create(AviInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategroiesBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hitMainCategoryApi()
//        binding.rvCategoriesAdmin.adapter = object : AdapterView.OnItemClickListener {
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                TODO("Not yet implemented")
//            }
//        }

//        binding.rvCategoriesAdmin.adapter =
//            AdapterView.OnItemClickListener { p0, p1, p2, p3 -> TODO("Not yet implemented")  }

        val activity: AdminHomeActivity? = activity as AdminHomeActivity?
        myDataFromActivity = activity!!.titleCustom

        binding.refreshCatAdmin.setOnRefreshListener {
            hitMainCategoryApi()
            adapter.notifyDataSetChanged()
            binding.refreshCatAdmin.isRefreshing = false
        }

        val dialog = Dialog(requireContext())
        binding.ivAddCategoriesAdmin.setOnClickListener {

            dialog.setContentView(R.layout.dialog_user_master)
            val dialogId = dialog.findViewById<EditText>(R.id.etIdUserMasterDialog)
            val dialogName = dialog.findViewById<EditText>(R.id.etNameUserMasterDialog)
            val dialogLL = dialog.findViewById<LinearLayout>(R.id.llUploadImgMainCategory)

            // image picker
            dialogLL.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            }
            dialog.show()
            val submit = dialog.findViewById<Button>(R.id.btSubmit)

            submit.setOnClickListener {
                postMainCategory(myDataFromActivity, dialogName.text.toString())
                dialog.dismiss()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null)
        filePath = FileUtil.from(requireContext(), data.data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun populatingData(data: mainCategoryModel) {
        val arrayData = ArrayList<CategoryImg>()
        for (i in 0 until data.categoryImg.size) {
            val dumy = data.categoryImg[i]
            Log.d("justMineAdapter", "populatingData: $data")
            arrayData.add(
                CategoryImg(
                    dumy.categoryImg,
                    dumy.categoryName,
                    dumy.categoryStatus,
                    dumy.id,
                    dumy.discountPrice,
                    dumy.price
                )
            )
        }

       CoroutineScope(Dispatchers.Main).async{
             adapter = recyclerAdapterCategories(arrayData,requireContext() )
            binding.rvCategoriesAdmin.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false )
            binding.rvCategoriesAdmin.adapter = adapter
        }
    }

    private fun hitMainCategoryApi() {
        binding.categoryProgressbar.visibility = View.VISIBLE

       CoroutineScope(Dispatchers.IO).async {
            val call = repo.getMainCategory()
            if (call.isSuccessful) {
                binding.categoryProgressbar.visibility = View.GONE
                populatingData(call.body()!!)
            } else {
                binding.categoryProgressbar.visibility = View.GONE
                Toasty.getToasty(requireContext(), "${call.errorBody()}")
            }
        }
    }

    private fun postMainCategory(id: String, name: String) {
        val filePath = filePath
        Log.d("mine_id ", "postMainCategory: $id")
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), filePath!!)
        val parts = MultipartBody.Part.createFormData("categoryImg", filePath.name, requestBody)
        val id = RequestBody.create("text/plain".toMediaTypeOrNull(), id)
        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val status = RequestBody.create("text/plain".toMediaTypeOrNull(), categoryStatus.toString())

        GlobalScope.launch {
            val call = repo.postMainCategory(id, parts, name, status)
            Log.d("respo", "postMainCategory: $call")
            if (call.isSuccessful)
                Toasty.getToasty(requireContext(),call.message())
            else
                Toasty.getToasty(requireContext(), call.errorBody().toString())
        }
    }
}