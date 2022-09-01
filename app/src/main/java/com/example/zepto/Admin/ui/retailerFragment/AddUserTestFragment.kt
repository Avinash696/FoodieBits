package com.example.zepto.Admin.ui.retailerFragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.adapter.adapterRetailerAddUser
import com.example.zepto.databinding.FragmentAddUserBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import com.example.zepto.module.retailerAddUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class AddUserTestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var listItems: ArrayList<String>
    private var dialog: Dialog? = null
    var adapter: adapterRetailerAddUser? = null
    val arrayData = ArrayList<retailerResponceAddUserModel>()
//    val arrayData = ArrayList<retrailerFirstAddUserModel>()
    var retailerData = ArrayList<retailerAddUserModel>()
    lateinit var selectedImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAdminApiData()
        dialog = Dialog(requireContext());
        dialog!!.setContentView(R.layout.dialog_adduser_retailer);

        val tempName = dialog!!.findViewById<EditText>(R.id.etNameAdduserRetailer)
        val tempImv = dialog!!.findViewById<ImageView>(R.id.ivDialogUploadPic)
        val submitBtn = dialog!!.findViewById<Button>(R.id.btSubmitAddUserRetailer)

        tempImv.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            galleryIntent.putExtra(Intent.)
            startActivityForResult(galleryIntent, 22)
        }
        binding.button1.setOnClickListener {
            dialog!!.show()
        }
        submitBtn.setOnClickListener {
//            arrayData.add(retailerResponceAddUserModel(R.drawable.profile.toString(), tempName.text.toString()))
            adapter!!.notifyDataSetChanged();
            dialog!!.dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        var imageStream: InputStream? = null
        if (requestCode == 22 && requestCode == AppCompatActivity.RESULT_OK && intent!!.data != null) {
            try {
                //Let's read the picked image -its URI
                val pickedImage: Uri? = intent.data

                //Let's read the image path using content resolver
                imageStream = requireContext().contentResolver.openInputStream(pickedImage!!)

                selectedImage = BitmapFactory.decodeStream(imageStream)
//                binding.ivPanCardUpload.setImageBitmap(selectedImage)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                if (imageStream != null) {
                    try {
                        imageStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        super.startActivityForResult(intent, requestCode)
    }

    private fun populatingData(arrayData: retailerResponceAddUserModel?) {

//        arrayData.add(retrailerFirstAddUserModel(R.drawable.profile.toString(), "John"))
        adapter = adapterRetailerAddUser(arrayData, requireContext())
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        binding.listView1.layoutManager = layoutManager
        binding.listView1.adapter = adapter
        adapter!!.notifyDataSetChanged();
    }

    private fun getAdminApiData() {
        val clinet = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = clinet.getRetailerAddUser()
            if (call.isSuccessful)
                for (i in 0 until call.body()!!.size) {
                    val data = call.body()!![i]
//                    retailerData.add(retailerAddUserModel(data.id,data.role,data.name,data.adminImgPic,data.password,data.email,data.mobileNo,data.address
//                        ,data.adhar,data.panCard,data.shopReqNo))
//                    populatingData(retailerData)
                    val locKatapa =
                    "http://56testing.club/imgFolder/uploads/admins/${data.adminImgPic}"
//                    arrayData.add(
//                        retrailerFirstAddUserModel(
//                            locKatapa,
//                            data.name
//                        )
//                    )
                    populatingData(call.body())

                    Log.d("rawat", "getAdminApiData Responce: $data")
                }
            else
                Log.d("rawat", "getAdminApiData: ${call.errorBody()}")
        }
    }
}
