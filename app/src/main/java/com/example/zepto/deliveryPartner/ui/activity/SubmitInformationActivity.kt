package com.example.zepto.deliveryPartner.ui.activity

import android.R.attr
import android.R.attr.bitmap
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivitySubmitInformationBinding
import com.example.zepto.db.RetrofitHelper
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class SubmitInformationActivity : AppCompatActivity() {
    var courses = arrayOf(
        "Scotty", "Car", "Cycle"
    )
    private val PICK_Adhar = 2
    private val PICK_PAN = 1
    private val TAG = "mydd"
    lateinit var rb: RadioGroup
    lateinit var rbVechile: RadioButton
    lateinit var rbPersonal: RadioButton
    lateinit var rbBank: RadioButton
    lateinit var spino: Spinner
    private var filePathPan: File? = null
    private var filePathAdhar: File? = null
    private lateinit var stVechile: String
    private lateinit var binding: ActivitySubmitInformationBinding
    val retro = RetrofitHelper.getClient().create(aviInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit_information)
        supportActionBar!!.title = "Delivery"
        init()
        setSpinner()
        spinnerOnclick()
        Log.d(TAG, "onCreate: ")
        //image pan
        binding.ivPanUpload.setOnClickListener {
            val panIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            panIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            startActivityForResult(panIntent, PICK_PAN)
        }
        binding.ivAdharUpload.setOnClickListener {
            val adharIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            adharIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            startActivityForResult(adharIntent, PICK_Adhar)
        }

        binding.spVechileType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d(TAG, "onItemSelected:${p0!!.getItemAtPosition(p2)} ")
                stVechile = p0.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(TAG, "onItemSelected nothing:${p0!!.selectedItem} ")
            }
        }


        //submit data on submit
        binding.btSaveSubmitFormDelivery.setOnClickListener {
            stVechile
            postDeliveryData(
                stVechile,
                binding.etVechileNo.text.toString(),
                binding.etVechileLincenseNo.text.toString(),
                binding.etUserNameDelivery.text.toString(),
                binding.etUserEmailDelivery.text.toString(),
                binding.etUserPanDelivery.text.toString(),
                binding.etAdharCardDelivery.text.toString(),
                binding.etEmergencyNumberDelivery.text.toString(),
                binding.etGuidanceNameDelivery.text.toString(),
                binding.etBankNameDelivery.text.toString(),
                binding.etAccountNoDelivery.text.toString(),
                binding.etIfscCodeDelivery.text.toString(),
                binding.etBranchNameDelivery.text.toString()
            )
        }

    }

    private fun postDeliveryData(
        vechileType: String,
        vechileNo: String,
        vechileLincense: String,
        userName: String,
        userEmail: String,
        panCard: String,
        adharCard: String,
        emergencyNo: String,
        emergencyName: String,
        bankName: String,
        accountNo: String,
        ifscCode: String,
        branchName: String
    ) {
        Log.d("tempCheck", "postDeliveryData: $vechileType $vechileNo $vechileLincense $userName $userEmail $panCard" +
                " $adharCard $emergencyNo $emergencyName $bankName $accountNo $ifscCode $branchName $filePathAdhar $filePathPan")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_PAN) {
            filePathPan = FileUtil.from(this, data!!.data)
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, data!!.data)
            binding.ivPanCardDelivery.setImageBitmap(bitmap)
        } else if (requestCode == PICK_Adhar) {
            filePathAdhar = FileUtil.from(this, data!!.data)
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, data!!.data)
            binding.ivAdharCardDelivery.setImageBitmap(bitmap)
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun init() {
        spino = findViewById(R.id.spVechileType)
        rbVechile = findViewById(R.id.rbVechile)
        rbPersonal = findViewById(R.id.rbPersonal)
        rbBank = findViewById(R.id.rbBank)
        rb = findViewById(R.id.rb)
    }

    private fun setSpinner() {
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )
        ad.setDropDownViewResource(
            android.R.layout
                .simple_spinner_dropdown_item
        )
        spino.adapter = ad
    }

    private fun spinnerOnclick() {
        rb.setOnCheckedChangeListener { _, optionId ->
            run {
                when (optionId) {
                    R.id.rbVechile -> {
                        binding.rvDeliveryVechile.visibility = View.VISIBLE
                        binding.rvDeliveryBankDetail.visibility = View.GONE
                        binding.rvDeliveryPersonalDetail.visibility = View.GONE
                    }
                    R.id.rbPersonal -> {
                        binding.rvDeliveryPersonalDetail.visibility = View.VISIBLE
                        binding.rvDeliveryVechile.visibility = View.GONE
                        binding.rvDeliveryBankDetail.visibility = View.GONE
                    }
                    R.id.rbBank -> {
                        binding.rvDeliveryBankDetail.visibility = View.VISIBLE
                        binding.rvDeliveryVechile.visibility = View.GONE
                        binding.rvDeliveryPersonalDetail.visibility = View.GONE
                    }
                    else -> {
                        Log.d(TAG, "else : ")
                    }
                }
            }
        }
    }
    private fun createDelivery(){
        val call = retro
    }
}

