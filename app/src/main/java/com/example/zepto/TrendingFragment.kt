package com.example.zepto

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.Admin.ui.activity.AdminHomeActivity
import com.example.zepto.adapter.adapterTrendingAdmin
import com.example.zepto.databinding.FragmentTrendingBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.trendingResponceModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TrendingFragment : Fragment() {
    private lateinit var binding: FragmentTrendingBinding
    private lateinit var dialog: Dialog
    private lateinit var currentUser: String
    private lateinit var  img:ImageView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var filePath: File? = null
    val PICK_IMAGE = 777
    val repo = RetrofitHelper.getClient().create(aviInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        currentUser = (activity as AdminHomeActivity?)!!.titleCustom
//        Log.d("deleteit", "onCreate: ${ }")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_trending, container, false)
        binding = FragmentTrendingBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialogRemoveCat()
        binding.ivAdminAddCat.setOnClickListener {
            dialogAddCat()
            dialog.show()
             img = dialog.findViewById(R.id.ivTrendingImage)
            val name = dialog.findViewById<EditText>(R.id.etTrendingName)
            val price = dialog.findViewById<EditText>(R.id.etTrendingPrice)
            val discountPrice = dialog.findViewById<EditText>(R.id.etTrendingDiscount)
            val qty = dialog.findViewById<EditText>(R.id.etTrendingQunatity)
            val btnSubmit = dialog.findViewById<Button>(R.id.btSubmitTrending)
            //img picker
            img.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            }
            btnSubmit.setOnClickListener {
                if (filePath != null) {
                    postMainTrending(
                        name.text.toString(),
                        qty.text.toString(),
                        discountPrice.text.toString(),
                        price.text.toString()
                    )
                    dialog.dismiss()

                } else {
                    Toast.makeText(requireContext(), "Plz select img First ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.ivAdminRemoveCat.setOnClickListener {
            dialogAddCat()
            dialog.show()
        }
        getTrendingItem()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData(data: trendingResponceModel?) {
        Log.d("trend", "populatingData: $data")
        val arrayData = ArrayList<SubCategoryImgX>()
        for (i in 0 until data!!.subCategoryImg.size) {
            val dumy = data.subCategoryImg[i]
            arrayData.add(
                SubCategoryImgX(
                    currentUser, dumy.productImg, dumy.productName, dumy.productQty,
                    dumy.id, dumy.discountedPrice, dumy.priceShow
                )
            )
        }
        requireActivity().runOnUiThread {
            val adapter = adapterTrendingAdmin(arrayData, requireContext())
            binding.rvTrendingAdmin.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            binding.rvTrendingAdmin.adapter = adapter
        }
    }

    private fun dialogAddCat() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_trending_admin)
    }

    private fun dialogRemoveCat() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_user_master)
    }

    private fun getTrendingItem() {
        Log.d("trendingTag", "getTrendingItem: ")

        GlobalScope.launch {
            val call = repo.getMainTrending()
            Log.d("trendingTag", "getTrendingItem: $call")
            if (call.isSuccessful) {
                Log.d("trendingTag", "getTrendingItem:${Gson().toJson(call.body())} ")
                populatingData(call.body())
            } else
                Log.d("trendingTag", "getTrendingItem:${call.errorBody()} ")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        filePath = FileUtil.from(requireContext(), data!!.data)
        val uri = data.data
        val bitmap =
            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
        img.setImageBitmap(bitmap)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun postMainTrending(
        productNameParm: String,
        productQtyParm: String,
        discountedPriceParm: String,
        priceShowParm: String
    ) {
        val filePath = filePath
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("productImg", filePath.name, requestBody)
        val idUser = RequestBody.create(MediaType.parse("text/plain"), currentUser)
        val productName = RequestBody.create(MediaType.parse("text/plain"), productNameParm)
        val productQty = RequestBody.create(MediaType.parse("text/plain"), productQtyParm)
        val discountedPrice = RequestBody.create(MediaType.parse("text/plain"), discountedPriceParm)
        val priceShow = RequestBody.create(MediaType.parse("text/plain"), priceShowParm)
//        Log.d("beforePost", "postMainCategory: ${parts.body()} $status $subCategoryName")

        GlobalScope.launch {
            Log.d("respo", "postMainTrending:  $idUser,\n" +
                    " $filePath,\n" +
                    " $currentUser,\n" +
                    " $productNameParm,\n" +
                    " $productQtyParm,\n" +
                    " $discountedPriceParm  $priceShowParm")
            val call = repo.postMainTrending(
                idUser,
                parts,
                productName,
                productQty,
                discountedPrice,
                priceShow
            )
            Log.d("respo", "postMainCategory: $call")
            if (call.isSuccessful)
                Log.d("respo", "postMainCategory: Success ${call.body()}")
            else
                Log.d("respo", "postMainCategory:  Error ${call.message()}")
        }
    }
}
