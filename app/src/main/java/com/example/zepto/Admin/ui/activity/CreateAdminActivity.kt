package com.example.zepto.Admin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.test.FileUtil
import com.example.test.AviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivityCreateAdminBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CreateAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAdminBinding
    private var filePath: File? = null
    lateinit var tempNameStr: String
    lateinit var tempSpRole: String
    lateinit var tempPassword: String
    lateinit var tempEmail: String
    lateinit var tempMobileNo: String
    lateinit var tempAddress: String
    lateinit var tempAdhar: String
    lateinit var tempPanCard: String
    lateinit var tempShopReg: String
    lateinit var submitBtn: Button
//    var spinnerArray = arrayOf("admin", "wladmin", "retailer")
    var spinnerArray = arrayOf( "wladmin", "retailer")
    lateinit var titleLoginUser:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_admin)


        //get intent
        val intentTitle = intent
         titleLoginUser = intentTitle.getStringExtra("adminTitleCheck")!!

        //action bar
        supportActionBar!!.title = "Create WlAdmin"
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.color.blue))

        init()

        //intent to select img
        binding.ivDialogUploadPic.setOnClickListener {
            val galleryIntent =
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
            startActivityForResult(galleryIntent, 100)
        }

        submitBtn.setOnClickListener {

            createUser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && (data != null)) {
            filePath = FileUtil.from(this, data.data)

            val uri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            binding.ivDialogUploadPic.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun init() {
         tempNameStr = binding.etNameAdduserDialogRetailer.text.toString()
        val tempImv = binding.ivDialogUploadPic

        val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
        //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spRoleAdduserDialogRetailer.adapter = spinnerArrayAdapter
//           binding.spRoleAdduserDialogRetailer.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
//               override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                   Log.d("spinner test", "onItemSelected: ${p0!!.getItemAtPosition(p2)}")
//               }
//
//               override fun onNothingSelected(p0: AdapterView<*>?) {
//                   TODO("Not yet implemented")
//               }
//
//           }
         tempPassword = binding.etPassAdduserDialogRetailer.text.toString()
         tempEmail = binding.etEmailAdduserDialogRetailer.text.toString()
         tempMobileNo = binding.etMobileAdduserDialogRetailer.text.toString()
         tempAddress = binding.etAddressAdduserDialogRetailer.text.toString()
         tempAdhar = binding.etAdharAdduserDialogRetailer.text.toString()
         tempPanCard = binding.etPanAdduserDialogRetailer.text.toString()
         tempShopReg = binding.etShopAdduserDialogRetailer.text.toString()
         submitBtn = findViewById(R.id.btSubmitAddUserRetailer)
    }

    private fun createUser() {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), filePath!!)
        val parts = MultipartBody.Part.createFormData("imgFile", filePath!!.name, requestBody)
        val tempName =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etNameAdduserDialogRetailer.text.toString()
            )
        val tempRole =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                titleLoginUser
            )
        val tempPassword =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etPassAdduserDialogRetailer.text.toString()
            )
        val tempEmail =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etEmailAdduserDialogRetailer.text.toString()
            )
        val tempMobileNo =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etMobileAdduserDialogRetailer.text.toString()
            )
        val tempAddress =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etAddressAdduserDialogRetailer.text.toString()
            )
        val tempAdhar =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etAdharAdduserDialogRetailer.text.toString()
            )
        val tempPanCard =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etPanAdduserDialogRetailer.text.toString()
            )
        val tempShopReg =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etShopAdduserDialogRetailer.text.toString()
            )
        //generic id
        val rnds = (0..1000).random()
        val id = "$tempRole$rnds$tempName"
        val tempId =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                id
            )
        val tempWhoCreated =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                titleLoginUser
            )

        //hit api now
        val client = RetrofitHelper.getClient().create(AviInterface::class.java)

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
            if (call.isSuccessful) {
                makeToast(true)
            } else {
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