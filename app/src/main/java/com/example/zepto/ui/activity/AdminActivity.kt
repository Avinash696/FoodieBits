package com.example.zepto.ui.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityAdminBinding
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val LOAD_IMAGE_RESULTS = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin)

        binding.btCategory.setOnClickListener {
            categories()
        }
        binding.btUpload.setOnClickListener {
            uploadMultiPart()
        }
    }

    private fun uploadMultiPart() {

    }
    private fun getPath(uri:Uri){
        var cursor = contentResolver.query(uri,null,null,null,null)
        cursor!!.moveToFirst()
        var doc_id = cursor.getString(0)
        doc_id =doc_id.substring(doc_id.lastIndexOf(":") + 1)
        cursor.close()

        cursor = contentResolver.query(
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,MediaStore.Images.Media._ID+"=", arrayOf(doc_id),null
        )
    }
    private fun categories() {
        val i = Intent(Intent.ACTION_PICK)
        i.type = "image/*"
//        startActivityForResult(i, LOAD_IMAGE_RESULTS)
        startActivityForResult(Intent.createChooser(i,"Complted Action"),LOAD_IMAGE_RESULTS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var ImageStream: InputStream? = null

        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            try {
                //Let's read the picked image -its URI
                val pickedImage: Uri? = data.data
                Log.d("rawat", "onActivityResult:$pickedImage ")
                //Let's read the image path using content resolver
                ImageStream = contentResolver.openInputStream(pickedImage!!)

                //Now let's set the GUI ImageView data with data read from the picked file
                val selectedImage = BitmapFactory.decodeStream(ImageStream)
                binding.ivRest.setImageBitmap(selectedImage)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                if (ImageStream != null) {
                    try {
                        ImageStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}