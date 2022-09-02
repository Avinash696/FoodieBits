package com.example.test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityAvi2Binding
import com.example.zepto.db.RetrofitHelper
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class aviActivity : AppCompatActivity() {
    private val TAG = "kant"
    private var filePath : File? = null
    private lateinit var binding: ActivityAvi2Binding
    private lateinit var layout: View

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_avi2)
        layout = binding.root

        //select img
        binding.buttonUploadImage.setOnClickListener {
            //if everything is ok we will open image chooser

            onClickRequestPermission(it)
            //if everything is ok we will open image chooser
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uri = data!!.data
//        Log.d(TAG, "onActivityResult: ${uri!!.path}")
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        binding.imageView.setImageBitmap(bitmap)
        //show img real
        val selectedImageUri: Uri? = data.data
        filePath = FileUtil.from(this,data.data)
        hit()
        super.onActivityResult(requestCode, resultCode, data)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun hit() {
        Log.d(TAG, "hit: $filePath")

        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("pic", filePath!!.name, requestBody)

        val someData =
            RequestBody.create(MediaType.parse("text/plain"), binding.editTextTags.text.toString())
        Log.d("somedata", "hit:$someData $parts")
        val retrofit = RetrofitHelper.getClient().create(aviInterface::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val call = retrofit.uploadUrImg(someData, parts)

            if (call.isSuccessful) {
                if (call.body()?.error == true)
                    Log.d(TAG, " not success: ${call.body()?.message}")
                else
                    Log.d(TAG, "success: ${call.body()?.message}")

            } else {
                Log.d(TAG, "hit: error ${call.errorBody()}")
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


    private fun onClickRequestPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                layout.showSnackbar(
                    view,
                    getString(R.string.permission_granted),
                    Snackbar.LENGTH_INDEFINITE,
                    null
                ) {}
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
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
}