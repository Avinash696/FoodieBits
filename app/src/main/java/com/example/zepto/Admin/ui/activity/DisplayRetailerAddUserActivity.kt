package com.example.zepto.Admin.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.zepto.R
import com.example.zepto.databinding.ActivityDisplayRetailerAddUserBinding
import java.io.File


class DisplayRetailerAddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayRetailerAddUserBinding
//    val imgPath = "https://www.google.com/search?q=images&rlz=1C1JJTC_enIN1013IN1013&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj5_ujTjPT5AhWrZWwGHXo0B_oQ_AUoAnoECAEQBA&biw=1463&bih=672&dpr=1.75#imgrc=hZhe5urtcqVCNM"

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
        val emailKey = intentData.getStringExtra("emailKey")
        val idKey = intentData.getStringExtra("idKey")
        val mobileNoKey = intentData.getStringExtra("mobileNoKey")
        val nameKey = intentData.getStringExtra("nameKey")
        val panCardKey = intentData.getStringExtra("panCardKey")
        val roleKey = intentData.getStringExtra("roleKey")
        val shopReqNoKey = intentData.getStringExtra("shopReqNoKey")
        loadLogo(adminImgPicKey)

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
        Glide.with(this)
            .setDefaultRequestOptions(RequestOptions())
            .load(imgPath)
            .timeout(60000)
            .placeholder(R.drawable.b11)
            .error(R.drawable.b6)
            .into(binding.ivDisplayPic);

    }
}