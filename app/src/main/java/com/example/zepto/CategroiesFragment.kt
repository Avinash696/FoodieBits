package com.example.zepto

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.Admin.ui.activity.AdminHomeActivity
import com.example.zepto.adapter.recyclerAdapterCategories
import com.example.zepto.databinding.FragmentCategroiesBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.CategoryImg
import com.example.zepto.model.mainCategoryModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategroiesFragment : Fragment() {
    private lateinit var binding: FragmentCategroiesBinding
    private var param1: String? = null
    private var param2: String? = null
    val PICK_IMAGE = 111
    val categoryStatus = 1
    private var filePath: File? = null
    lateinit var myDataFromActivity: String
    private val repo = RetrofitHelper.getClient().create(aviInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
        val activity: AdminHomeActivity? = activity as AdminHomeActivity?
        myDataFromActivity = activity!!.titleCustom
        Log.d("checkiMg", "onActivityResult: $myDataFromActivity")



        val dialog = Dialog(requireContext())
        binding.ivAddCategoriesAdmin.setOnClickListener {
            Toast.makeText(requireContext(), "Added ", Toast.LENGTH_SHORT).show()

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
        filePath = FileUtil.from(requireContext(), data!!.data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun populatingData(data: mainCategoryModel) {
        val arrayData = ArrayList<CategoryImg>()
        for (i in 0 until data.categoryImg.size) {
            val dumy = data.categoryImg[i]
            arrayData.add(
                CategoryImg(
                    dumy.categoryImg,
                    dumy.categoryName,
                    dumy.categoryStatus,
                    dumy.id
                )
            )
        }

       GlobalScope.launch(Dispatchers.Main) {
            val adapter = recyclerAdapterCategories(arrayData,requireContext() )
            binding.rvCategoriesAdmin.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            binding.rvCategoriesAdmin.adapter = adapter
           adapter.notifyDataSetChanged()
        }
    }

    private fun hitMainCategoryApi() {
        GlobalScope.launch {
            val call = repo.getMainCategory()
            if (call.isSuccessful) {
                val gson = Gson()
                Log.d("apiData", "hitMainCategoryApi:  Success${gson.toJson(call.body()!!)}")

                populatingData(call.body()!!)
            } else
                Log.d("apiData", "hitMainCategoryApi: error ${call.errorBody()}")
        }
    }

    private fun postMainCategory(id: String, name: String) {
        val filePath = filePath
        val r = Random()
        Log.d("mine_id ", "postMainCategory: $id")
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("categoryImg", filePath.name, requestBody)
        val id = RequestBody.create(MediaType.parse("text/plain"), id)
        val name = RequestBody.create(MediaType.parse("text/plain"), name)
        val status = RequestBody.create(MediaType.parse("text/plain"), categoryStatus.toString())

        GlobalScope.launch {
            val call = repo.postMainCategory(id, parts, name, status)
            Log.d("respo", "postMainCategory: $call")
            if (call.isSuccessful)
                Log.d("respo", "postMainCategory: Success ${call.body()}")
            else
                Log.d("respo", "postMainCategory:  Error ${call.message()}")
        }
    }
}