package com.example.test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityAvi2Binding
import com.example.zepto.db.RetrofitHelper
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class aviActivity : AppCompatActivity() {
    private val TAG = "aviraw"
    private var filePath = ""
    private lateinit var binding: ActivityAvi2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_avi2)
        //select img
        binding.buttonUploadImage.setOnClickListener {
            //if everything is ok we will open image chooser

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
        val s: String? = getRealPathFromURI(selectedImageUri)
        filePath = s.toString()
        hit()
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun hit(){
        val file   = File(filePath)
        val  requestBody  =  RequestBody.create(MediaType.parse("image/*"),file)
        val parts = MultipartBody.Part.createFormData("id",file.name,requestBody)

        val someData = RequestBody.create(MediaType.parse("text/plain"),binding.editTextTags.text.toString())

        val retrofit =  RetrofitHelper.getClient().create(aviInterface::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val call = retrofit.uploadUrImg(someData,parts)

            if(call.isSuccessful) {
                val jo = Gson().toJson(call.body())
                Log.d(TAG, "hit success: $jo")
                Log.d(TAG, "hit success: $call")
                Log.d(TAG, "hit: error ${call.errorBody()}")
            }
            else {
                Log.d(TAG, "hit: error ${call.errorBody()}")
            }
        }
    }
    private fun getRealPathFromURI(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(uri, projection, null, null, null)
        val column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }
}