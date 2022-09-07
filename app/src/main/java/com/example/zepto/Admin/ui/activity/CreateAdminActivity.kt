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
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivityCreateAdminBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CreateAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAdminBinding
    private var filePath: File? = null
    lateinit var tempName: String
    lateinit var tempSpRole: String
    lateinit var tempPassword: String
    lateinit var tempEmail: String
    lateinit var tempMobileNo: String
    lateinit var tempAddress: String
    lateinit var tempAdhar: String
    lateinit var tempPanCard: String
    lateinit var tempShopReg: String
    lateinit var submitBtn: Button
    var spinnerArray = arrayOf("admin", "wladmin", "retailer")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_admin)

        //action bar
        supportActionBar!!.title = "Create User"

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

            Log.d("newAct", "onActivityResult:$filePath ")

            val uri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            binding.ivDialogUploadPic.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun init() {
         tempName = binding.etNameAdduserDialogRetailer.text.toString()
        val tempImv = binding.ivDialogUploadPic

        val spinnerArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
        //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spRoleAdduserDialogRetailer.adapter = spinnerArrayAdapter
           binding.spRoleAdduserDialogRetailer.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
               override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                   Log.d("spinner test", "onItemSelected: ${p0!!.getItemAtPosition(p2)}")
               }

               override fun onNothingSelected(p0: AdapterView<*>?) {
                   TODO("Not yet implemented")
               }

           }
         tempPassword = binding.etPassAdduserDialogRetailer.text.toString()
         tempEmail = binding.etEmailAdduserDialogRetailer.text.toString()
         tempMobileNo = binding.etMobileAdduserDialogRetailer.text.toString()
         tempAddress = binding.etAddressAdduserDialogRetailer.text.toString()
         tempAdhar = binding.etAdharAdduserDialogRetailer.text.toString()
         tempPanCard = binding.etPanAdduserDialogRetailer.text.toString()
         tempShopReg = binding.etShopAdduserDialogRetailer.text.toString()
         submitBtn = findViewById<Button>(R.id.btSubmitAddUserRetailer)
    }

    private fun createUser() {
        Log.d("url", "dialogCreateUser above:$filePath ")
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("imgFile", filePath!!.name, requestBody)
        Log.d("rawat", "dialogCreateUser: $parts")
        Log.d("url", "dialogCreateUser below:$filePath  ${parts.body()}")
        val tempName =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etNameAdduserDialogRetailer.text.toString()
            )
        val tempRole =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.spRoleAdduserDialogRetailer.selectedItem.toString()
            )
        val tempPassword =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etPassAdduserDialogRetailer.text.toString()
            )
        val tempEmail =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etEmailAdduserDialogRetailer.text.toString()
            )
        val tempMobileNo =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etMobileAdduserDialogRetailer.text.toString()
            )
        val tempAddress =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etAddressAdduserDialogRetailer.text.toString()
            )
        val tempAdhar =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etAdharAdduserDialogRetailer.text.toString()
            )
        val tempPanCard =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etPanAdduserDialogRetailer.text.toString()
            )
        val tempShopReg =
            RequestBody.create(
                MediaType.parse("text/plain"),
                binding.etShopAdduserDialogRetailer.text.toString()
            )
        //generic id
        val rnds = (0..1000).random()
        val id = "$tempRole$rnds$tempName"
        val tempId =
            RequestBody.create(
                MediaType.parse("text/plain"),
                id
            )

        Log.d("customid", "dialogCreateUser: $id")

        //hit api now
        val client = RetrofitHelper.getClient().create(aviInterface::class.java)
        Log.d("postdialog", "createUser: $tempId $tempRole $tempName $tempPassword $tempEmail $tempMobileNo $tempAddress " +
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
                parts
            )
            Log.d("rawat", "dialogCreateUser: ")
            if (call.isSuccessful) {
                Log.d("postdialog", "dialogCreateUser: Success ${call.body()!!.message}")
                makeToast(true)
            } else {
                Log.d("postdialog", "dialogCreateUser: Error  ${call.body()!!.error}")
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