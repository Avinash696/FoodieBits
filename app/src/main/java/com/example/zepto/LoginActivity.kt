package com.example.zepto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.Admin.ui.activity.AdminHomeActivity
import com.example.zepto.Admin.ui.activity.RetailerAdminActivity
import com.example.zepto.databinding.ActivityLoginBinding
import com.example.zepto.deliveryPartner.ui.activity.SubmitInformationActivity
import com.example.zepto.ui.activity.AdminActivity
import com.example.zepto.ui.activity.HomeActivity
import com.example.zepto.ui.activity.RegistrationActivity
import com.example.zepto.ui.activity.SubAdminActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btlogin.setOnClickListener {
            authenticateUser()
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun authenticateUser() {
        val stName = binding.etName.text.toString()
        val stPass = binding.etPassword.text.toString()
        Log.d("rawat", "authenticateUser: $stName  $stPass")

//        if ((stName == "bpind") && (stPass == "bpind")) {
//        }
        if (stName.isNotEmpty() && stPass.isNotEmpty()) {
            if ((stName == "admin") && (stPass == "admin")) {
                startActivity(Intent(this, AdminHomeActivity::class.java))
            } else if (stName == "avi" && stPass == "avi") {
                //client add (later)
                startActivity(Intent(this, HomeActivity::class.java))
            } else if (stName == "subadmin" && stPass == "subadmin") {
                startActivity(Intent(this, SubAdminActivity::class.java))
            }
            else if (stName == "retailer" && stPass == "retailer") {
                startActivity(Intent(this, RetailerAdminActivity::class.java))
            }
            else if (stName == "delivery" && stPass == "delivery") {
                startActivity(Intent(this, SubmitInformationActivity::class.java))
            }
        } else {
            Log.d("rawat", "authenticateUser: Enter all fields  ")
        }

    }
}