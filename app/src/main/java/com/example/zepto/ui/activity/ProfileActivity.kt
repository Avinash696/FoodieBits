package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.adapter.profileAddAddressAdapter
import com.example.zepto.constant.constants.choiceInsertProfileFlag
import com.example.zepto.databinding.ActivityProfileBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.addressUserResponceModel
import com.example.zepto.model.profileSavedAddressModel
import com.example.zepto.viewModel.ItemCountViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var dd: String = "Default Location"
    private lateinit var filepath: File
    private lateinit var bottomNavHome: BottomNavigationView
    private val TAG = "profileActivity"
    private lateinit var countViewModel: ItemCountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        countViewModel = ViewModelProvider(this)[ItemCountViewModel::class.java]
        init()
        val profileData = intent
        dd = if ((profileData.getStringExtra("ProfileData")) == null) {
            "Default Location"
        } else ({
            profileData.getStringExtra("ProfileData")
        }).toString()
        Log.d("urProfileScreendata", "onCreate: ${profileData.getStringExtra("ProfileData")}")
//        dd= profileData.getStringExtra("ProfileData")!!


        binding.ivAddprofileAddress.setOnClickListener {
            val intent = Intent(this, AddDeliveryAddressActivity::class.java)
            intent.putExtra("choiceKey", choiceInsertProfileFlag)
            startActivity(intent)
        }
        bottomNav()
        binding.ivProfileEditUpdate.setOnClickListener {
            editUpdateProfile()
        }


        //img Picker
        binding.ivProfileUploadPic.setOnClickListener {

            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uri = data!!.data

        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        binding.ivProfileImg.setImageBitmap(bitmap)

        filepath = FileUtil.from(this, data.data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun init() {
        bottomNavHome = binding.bottomNavigation
    }

    private fun bottomNav() {
        bottomNavHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.menu_category -> {
                    dialogCategories()
//                    startActivity(Intent(this, AdminActivity::class.java))
                    true
                }
                R.id.menu_order -> {
                    startActivity(Intent(this, OrderSummaryActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.menu_help -> {
                    startActivity(Intent(this, FaqsActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun dialogCategories() {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        dialog.show()
    }

    private fun setProfile(data: addressUserResponceModel?) {
        val arrayList = ArrayList<profileSavedAddressModel>()
        for (i in 0 until data!!.categoryImg.size) {
            val dd = data.categoryImg[i]
            arrayList.add(profileSavedAddressModel(dd.id,"work", dd.address))
        }
        GlobalScope.launch(Dispatchers.Main) {
            binding.rvProfileAddress.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            val arrayAdapter = profileAddAddressAdapter(applicationContext, arrayList)
            binding.rvProfileAddress.adapter = arrayAdapter
        }
    }

    private fun editUpdateProfile() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_update_profile)
        dialog.show()

        val name = dialog.findViewById<EditText>(R.id.etNameProfileDialog)
        val mobile = dialog.findViewById<EditText>(R.id.etMobileProfileDialog)
        val btnSubmit = dialog.findViewById<Button>(R.id.btSubmitProfile)

        btnSubmit.setOnClickListener {
            binding.tvProfileName.text = name.text.toString()
            binding.tvProfilePhone.text = mobile.text.toString()
            dialog.dismiss()
        }
    }

    override fun onStart() {
        getSavedAddress()
        super.onStart()
    }

    private fun getSavedAddress() {
        val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch {
            val call = repo.getUserAddress()
            if (call.isSuccessful)
                setProfile(call.body())
            else
                Log.d(TAG, "getSavedAddress: ${call.errorBody()}")
        }
    }
}