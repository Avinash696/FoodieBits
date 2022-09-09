package com.example.zepto.Admin.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityDisplayRetailerAddUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class DisplayRetailerAddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayRetailerAddUserBinding

    val imgPath = "http://56testing.club/imgFolder/uploads/admins/admin1.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_retailer_add_user)

        supportActionBar!!.title = "All Detail "


        val intentData = intent
        val addressKey = intentData.getStringExtra("addressKey")
        val adharKey = intentData.getStringExtra("adharKey")
        val passwordKey = intentData.getStringExtra("passwordKey")
        val adminImgPicKey = intentData.getStringExtra("adminImgPicKey")
        Log.d("urlcheck", "onCreate: $adminImgPicKey ")
        Log.d("urlcheck", "onCreate: $imgPath")
        val emailKey = intentData.getStringExtra("emailKey")
        val idKey = intentData.getStringExtra("idKey")
        val mobileNoKey = intentData.getStringExtra("mobileNoKey")
        val nameKey = intentData.getStringExtra("nameKey")
        val panCardKey = intentData.getStringExtra("panCardKey")
        val roleKey = intentData.getStringExtra("roleKey")
        val shopReqNoKey = intentData.getStringExtra("shopReqNoKey")
        loadLogo(adminImgPicKey )

//        binding.ivDisplayPic.setImageURI(imgPath.toUri())
        binding.tvDisplayAddress.text = addressKey
        binding.tvDisplayAdhar.text = adharKey
        binding.tvDisplayPassword.text = passwordKey
        binding.tvDisplayEmail.text = emailKey
        binding.tvDisplayId.text = idKey
        binding.tvDisplayPhone.text = mobileNoKey
        binding.tvDisplayName.text = nameKey
        binding.tvDisplayPan.text = panCardKey
        binding.tvDisplayRole.text = roleKey
        binding.tvDisplayShopNo.text = shopReqNoKey

    }

    private fun loadLogo(url: String?) {
        val fullUrl = "http://56testing.club/imgFolder/uploads/admins/$url".trim()


        Picasso.get()
            .load(fullUrl)
            .into(binding.ivDisplayPic, object : Callback {
                override fun onSuccess() {
                    binding.pb.visibility = View.GONE
                    Log.d("mess", "Success: ")
                }

                override fun onError(e: Exception?) {
                    binding.pb.visibility = View.VISIBLE
                    Log.d("mess", "onError: ${e!!.message}")
                }
            });
    }
}