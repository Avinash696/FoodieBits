package com.example.zepto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivityAddProductFormBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class AddProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductFormBinding
    private var filePath: File? = null
    private val PICK_IMAGE = 155
    lateinit var id_form: String

    //    lateinit var name_form: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        id_form = intent.getStringExtra("SubFormIdKey")!!
//        name_form = intent.getStringExtra("SubFormNameKey")!!
        Log.d("flowListWala", "product form: $id_form ")
        supportActionBar!!.title = "Add Product"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product_form)

        //img picker
        binding.ivImgFormUp.setOnClickListener {
            Log.d("mydd", "onCreate: u clicked ")
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

        binding.btAddMoreAlikeItem.setOnClickListener {

            when (binding.llItemAlike.visibility) {
                View.GONE -> {
                    binding.llItemAlike.visibility = View.VISIBLE
                    return@setOnClickListener
                }
                View.VISIBLE -> {
                    binding.llItemAlike.visibility = View.GONE
                    return@setOnClickListener
                }
            }
        }

        binding.btSubmitProduct.setOnClickListener {
            postMainProduct(id_form)
            Log.d("Reqmaina", "onCreate: btn Submitted")
            Toast.makeText(this, "Summited", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, AllItemSubAdminActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            filePath = FileUtil.from(this, data!!.data)
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            binding.ivImgFormUp.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun postMainProduct(id_form: String) {
        val filePath = filePath
        Log.d("Reqmaina", "postMainProduct: $filePath")
        val subCategoryId = 1278
        val addProductId = 1278
        val addProductStatus = 1
        val rand = Random()

        Log.d("flowListWala", "postMainProduct: ${rand.nextInt(1000)}")
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("addProductImg", filePath.name, requestBody)

        val mainSubCategoryId =
            RequestBody.create(MediaType.parse("text/plain"), id_form.toString())
        val subProductId =
            RequestBody.create(MediaType.parse("text/plain"), rand.nextInt(1000).toString())
        val subProductName =
            RequestBody.create(MediaType.parse("text/plain"), binding.etProductName.text.toString())
        val subProductStatus =
            RequestBody.create(MediaType.parse("text/plain"), addProductStatus.toString())
        val addProductQuantity = RequestBody.create(
            MediaType.parse("text/plain"),
            binding.etproductQuantity.text.toString()
        )
        val addProductPrice = RequestBody.create(
            MediaType.parse("text/plain"),
            binding.etproductPrice.text.toString()
        )
        val addProductDescription = RequestBody.create(
            MediaType.parse("text/plain"),
            binding.etproductDescription.text.toString()
        )


        val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = repo.postMainSubProduct(
                parts,
                subProductName,
                mainSubCategoryId,
                subProductId,
                subProductStatus,
                addProductPrice,
                addProductQuantity,
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