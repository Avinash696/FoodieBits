package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.zepto.R
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class JustTestItActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var ivImgFormUpTEst: ImageView
    private var filePath: File? = null
    private val PICK_IMAGE = 1523
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_just_test_it)


        btn = findViewById(R.id.btnHitApi)
        ivImgFormUpTEst = findViewById(R.id.ivImgFormUpTEst)
        ivImgFormUpTEst.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
        btn.setOnClickListener {
            postMainProduct()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        filePath = FileUtil.from(this, data!!.data)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
        ivImgFormUpTEst.setImageBitmap(bitmap)

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun postMainProduct() {
        val filePath = filePath
        Log.d("Reqmaina", "postMainProduct: $filePath")
//        val subCategoryId = 1278
//        val addProductId = 1278
//        val addProductStatus = 1
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), filePath!!)
        val addProductImg =
            MultipartBody.Part.createFormData("addProductImg", filePath.name, requestBody)
        val addProductName = RequestBody.create("text/plain".toMediaTypeOrNull(), "Product Name")
        val subCategoryId = RequestBody.create("text/plain".toMediaTypeOrNull(), "1279")
        val addProductId = RequestBody.create("text/plain".toMediaTypeOrNull(), "aa")
        val addProductStatus = RequestBody.create("text/plain".toMediaTypeOrNull(), "1")
        val addProductQuantity = RequestBody.create("text/plain".toMediaTypeOrNull(), "21")
        val addProductPrice = RequestBody.create("text/plain".toMediaTypeOrNull(), "32")
        val addProductDescription = RequestBody.create("text/plain".toMediaTypeOrNull(), "bbb")


        val repo = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = repo.postMainSubProduct(
                addProductImg,
                addProductName,
                subCategoryId,
                addProductId,
                addProductStatus,
                addProductQuantity,
                addProductPrice,
                addProductDescription
            )
            Log.d("Reqmaina", "postMainCategory: $call")
            if (call.isSuccessful)
                Log.d("Reqmaina", "postMainCategory: Success ${call.body()}")
            else
                Log.d("Reqmaina", "postMainCategory:  Error ${call.message()}")
        }
    }
}