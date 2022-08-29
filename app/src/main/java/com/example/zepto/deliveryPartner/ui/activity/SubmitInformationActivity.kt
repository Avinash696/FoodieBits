package com.example.zepto.deliveryPartner.ui.activity

import android.R.attr
import android.R.attr.bitmap
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivitySubmitInformationBinding
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class SubmitInformationActivity : AppCompatActivity() {
    var courses = arrayOf(
        "Scotty", "Car", "Cycle"
    )
    private val PICK_FROM_CAMERA = 1
    private val PICK_FROM_GALLARY = 2
    private val TAG = "mydd"
    lateinit var rb: RadioGroup
    lateinit var rbVechile: RadioButton
    lateinit var rbPersonal: RadioButton
    lateinit var rbBank: RadioButton
    lateinit var spino: Spinner
    private lateinit var binding: ActivitySubmitInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit_information)
        supportActionBar!!.title = "Delivery"
        init()
        setSpinner()
        spinnerOnclick()
        //image pan
        binding.ivPanUpload.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            startActivityForResult(galleryIntent, PICK_FROM_GALLARY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var imageStream: InputStream? = null

        if (requestCode == PICK_FROM_GALLARY && resultCode == RESULT_OK && data != null) {
            try {
                //Let's read the picked image -its URI
                val pickedImage: Uri? = data.data

                //Let's read the image path using content resolver
                imageStream = contentResolver.openInputStream(pickedImage!!)

                //Now let's set the GUI ImageView data with data read from the picked file
                val count = data.clipData!!.itemCount
                for (nums in 1..count) {
                    Log.d("tttt", "onActivityResult: $nums")
                }
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                Log.d(TAG, "onActivityResult: ${selectedImage}")
                binding.ivPanCardUpload.setImageBitmap(selectedImage)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                if (imageStream != null) {
                    try {
                        imageStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
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
}

