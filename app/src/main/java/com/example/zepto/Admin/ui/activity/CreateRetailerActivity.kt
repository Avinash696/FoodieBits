package com.example.zepto.Admin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.test.FileUtil
import com.example.test.AviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivityCreateRetailerBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CreateRetailerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCreateRetailerBinding
    private var filePath: File? = null
    private lateinit var titleLoginUser:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_create_retailer)

        //get intent
        val intentTitle = intent
        titleLoginUser = intentTitle.getStringExtra("adminTitleCheck")!!


        Log.d("curentUSer", "onCreate:Retailer  $titleLoginUser")

        //action bar
        supportActionBar!!.title = "Create Retailer "
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.color.blue))

        //intent to select img
        binding.ivDialogUploadPicRetailer.setOnClickListener {
            val galleryIntent =
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
            startActivityForResult(galleryIntent, 100)
        }
        binding.btSubmitAddRtailerRetailer.setOnClickListener {

            createRtailer()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && (data != null)) {
            filePath = FileUtil.from(this, data.data)

            Log.d("newAct", "onActivityResult:$filePath ")

            val uri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            binding.ivDialogUploadPicRetailer.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun createRtailer() {
        Log.d("url", "dialogCreateRtailer above:$filePath ")
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("imgFile", filePath!!.name, requestBody)
        Log.d("rawat", "dialogCreateRtailer: $parts")
        Log.d("url", "dialogCreateRtailer below:$filePath  ${parts.body()}")
        val tempName =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etNameAddRtailerDialogRetailer.text.toString()
            )
        val tempRole =
            RequestBody.create(
                MediaType.parse("text/plain"),
                titleLoginUser
            )
        val tempPassword =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etPassAddRtailerDialogRetailer.text.toString()
            )
        val tempEmail =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etEmailAddRtailerDialogRetailer.text.toString()
            )
        val tempMobileNo =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etMobileAddRtailerDialogRetailer.text.toString()
            )
        val tempAddress =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etAddressAddRtailerDialogRetailer.text.toString()
            )
        val tempAdhar =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etAdharAddRtailerDialogRetailer.text.toString()
            )
        val tempPanCard =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etPanAddRtailerDialogRetailer.text.toString()
            )
        val tempShopReg =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etShopAddRtailerDialogRetailer.text.toString()
            )
        //generic id
        val rnds = (0..1000).random()
        val id = "$tempRole$rnds$tempName"
        val tempId =
            RequestBody.create(
                MediaType.parse("text/plain"),
                id
            )
        val tempWhoCreated =
            RequestBody.create(
                MediaType.parse("text/plain"),
                titleLoginUser
            )

        Log.d("customid", "dialogCreateRtailer: $id")

        //hit api now
        val client = RetrofitHelper.getClient().create(AviInterface::class.java)
        Log.d("postdialog", "createRtailer: $tempId $tempRole $tempName $tempPassword $tempEmail $tempMobileNo $tempAddress " +
                "$tempAdhar $tempPanCard $tempShopReg")
        GlobalScope.launch {
            val call = client.createRetailer(
                tempId,
                tempRole,
                tempName,
                tempPassword,
                tempEmail,
                tempMobileNo,
                tempAddress,
                tempAdhar,
                tempPanCard,
                tempShopReg,
                parts,
                tempWhoCreated
            )
            Log.d("rawat", "dialogCreateRtailer: ")
            if (call.isSuccessful) {
                Log.d("postdialog", "dialogCreateRtailer: Success ${call.body()!!.message}")
                makeToast(true)
            } else {
                Log.d("postdialog", "dialogCreateRtailer: Error  ${call.body()!!.error}")
                makeToast(false)
            }
        }
    }
    private fun makeToast(success: Boolean){
        if (success){
            Toast.makeText(this, "Success Add User", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "Failed User ", Toast.LENGTH_SHORT).show()
    }
}