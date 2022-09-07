package com.example.zepto.Admin.ui.retailerFragment

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.Admin.ui.activity.CreateAdminActivity
import com.example.zepto.R
import com.example.zepto.adapter.adapterRetailerAddUser
import com.example.zepto.databinding.FragmentAddUserBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import com.example.zepto.module.retailerAddUserModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class AddUserTestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var filePath: File? = null
    private lateinit var layout: View
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var listItems: ArrayList<String>
//    private var dialog: Dialog? = null
    var adapter: adapterRetailerAddUser? = null
    val arrayData = ArrayList<retailerResponceAddUserModel>()

    //    val arrayData = ArrayList<retrailerFirstAddUserModel>()
    var retailerData = ArrayList<retailerAddUserModel>()
    lateinit var selectedImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rnds = (0..1000).random()
        Log.d("tsadgas", "onCreate:$rnds ")
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        layout = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAdminApiData()
        binding.button1.setOnClickListener {
            val intent = Intent(requireContext(),CreateAdminActivity::class.java)
//            intent.putExtra("userId",(AddUserTestFragment))
            startActivity(intent)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val imageStream: InputStream? = null
        if (requestCode == 100 && requestCode == AppCompatActivity.RESULT_OK && data!!.data != null) {
            try {
                Log.d("url", "onresult:${data.data}")
                //Let's read the picked image -its URI
                filePath = FileUtil.from(requireContext(), data.data)
                Log.d("url", "onresult:$filePath  ")
                val uri = data!!.data
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
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
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun populatingData(arrayData: retailerResponceAddUserModel?) {

        adapter = adapterRetailerAddUser(arrayData, requireContext())
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        binding.listView1.layoutManager = layoutManager
        binding.listView1.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    private fun getAdminApiData() {
        val clinet = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = clinet.getRetailerAddUser()
            if (call.isSuccessful)
                for (i in 0 until call.body()!!.size) {
                    val data = call.body()!![i]
                    val locKatapa =
                        "http://56testing.club/imgFolder/uploads/admins/${data.adminImgPic}"
                    populatingData(call.body())

                    Log.d("rawat", "getAdminApiData Responce: $data")
                }
            else
                Log.d("rawat", "getAdminApiData: ${call.errorBody()}")
        }
    }


    private fun onClickRequestPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
//                layout.showSnackbar(
//                    view,
//                    getString(R.string.permission_granted),
//                    Snackbar.LENGTH_INDEFINITE,
//                    null
//                ) {}
                Log.d("permiss", "granted: ")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireContext() as Activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                layout.showSnackbar(
                    view,
                    getString(R.string.permission_required),
                    Snackbar.LENGTH_INDEFINITE,
                    getString(R.string.ok)
                ) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }
    }

    private fun View.showSnackbar(
        view: View,
        msg: String,
        length: Int,
        actionMessage: CharSequence?,
        action: (View) -> Unit
    ) {
        val snackbar = Snackbar.make(view, msg, length)
        if (actionMessage != null) {
            snackbar.setAction(actionMessage) {
                action(this)
            }.show()
        } else {
            snackbar.show()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }
}
