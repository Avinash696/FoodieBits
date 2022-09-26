package com.example.zepto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

class AddProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductFormBinding
    private var filePath: File? = null
    private val PICK_IMAGE = 155
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        Log.d("justINsideProduct", "onCreate: ${intent.getStringExtra("SubProductIdKey")}")
        supportActionBar!!.title = "Add Particular Item"
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
            postMainProduct(
                "ur Product",
                binding.etProductName.text.toString(),
                1,
                binding.etproductQuantity.text.toString(),
                binding.etproductPrice.text.toString(),
                binding.etproductDescription.text.toString()
            )
            Toast.makeText(this, "Summited", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AllItemSubAdminActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == PICK_IMAGE && data!!.data == null) {
            filePath = FileUtil.from(this, data.data)
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            binding.ivImgFormUp.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun postMainProduct(
        title: String,
        name: String,
        status: Int,
        quantity: String,
        price: String,
        description: String
    ) {
        val filePath = filePath

        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("subProductImg", filePath.name, requestBody)

        val mainSubCategoryId = RequestBody.create(MediaType.parse("text/plain"), title)
        val subProductId = RequestBody.create(MediaType.parse("text/plain"), name)
        val subProductName = RequestBody.create(MediaType.parse("text/plain"), name)
        val subProductStatus = RequestBody.create(MediaType.parse("text/plain"), name)
        val addProductQuantity = RequestBody.create(MediaType.parse("text/plain"), name)
        val addProductPrice = RequestBody.create(MediaType.parse("text/plain"), name)
        val addProductDescription = RequestBody.create(MediaType.parse("text/plain"), name)


        val quantity = RequestBody.create(MediaType.parse("text/plain"), quantity)
        val price = RequestBody.create(MediaType.parse("text/plain"), price)
        val description = RequestBody.create(MediaType.parse("text/plain"), description)
        val status = RequestBody.create(MediaType.parse("text/plain"), status.toString())


        val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
//        GlobalScope.launch(Dispatchers.IO) {
//            val call = repo.postMainSubProduct(
//                subCategoryId,
//                parts,
//                name,
//                status,
//                quantity,
//                price,
//                description
//            )
//            Log.d("respo", "postMainCategory: $call")
//            if (call.isSuccessful)
//                Log.d("respo", "postMainCategory: Success ${call.body()}")
//            else
//                Log.d("respo", "postMainCategory:  Error ${call.message()}")
//        }
    }

}