package com.example.zepto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test.aviInterface
import com.example.zepto.Admin.ui.activity.AdminHomeActivity
import com.example.zepto.Admin.ui.activity.RetailerAdminActivity
import com.example.zepto.databinding.ActivityLoginBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.deliveryPartner.ui.activity.SubmitInformationActivity
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModelItem
import com.example.zepto.ui.activity.AdminActivity
import com.example.zepto.ui.activity.HomeActivity
import com.example.zepto.ui.activity.RegistrationActivity
import com.example.zepto.ui.activity.SubAdminActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var stName: String
    lateinit var stPass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btlogin.setOnClickListener {
            stName = binding.etName.text.toString()
            stPass = binding.etPassword.text.toString()
            authenticate()
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun authenticate() {
        val client = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = client.authenticateAdmin()
            if (call.isSuccessful) {
                for (i in 0 until call.body()!!.size) {
                    val tempData = call.body()!![i]
                    val result = (tempData.id).filter { it.isLetter() }
                    fnwork((tempData.id),(tempData.password),result,tempData.adminImgPic)
                }
            } else {
                Log.d("login", "error: ")
            }
        }
    }

    private fun fnwork(id:String,password:String,result:String,imgPath:String) {
        Log.d("fnwork", "fileds  $password $id")
        when {
            (stName == id) && (stPass == password) && (result == "admin") -> {
                val intent = Intent(this, AdminHomeActivity::class.java)
                intent.putExtra("adminTitle",id)
                intent.putExtra("adminImg",imgPath)
                startActivity(intent)
            }
            (stName == id) && (stPass == password) && (result == "wladmin") -> {
                val intent = Intent(this, SubAdminActivity::class.java)
                intent.putExtra("wladminTitle",id)
                intent.putExtra("adminImg",imgPath)
                startActivity(intent)
            }
            (stName.equals(id)) && (stPass.equals(password)) && (result == "retailer") -> {
                val intent = Intent(this, RetailerAdminActivity::class.java)
                intent.putExtra("retailerTitle",id)
                intent.putExtra("adminImg",imgPath)
                startActivity(intent)
            }
            (stName == "avi") && (stPass == "avi") || result == "home" -> {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("retailerTitle",id)
                intent.putExtra("adminImg",imgPath)
                startActivity(intent)
            }
            (stName == "delivery") && (stPass == "delivery") || result == "delivery" -> {
                val intent = Intent(this, SubmitInformationActivity::class.java)
                intent.putExtra("retailerTitle",id)
                intent.putExtra("adminImg",imgPath)
                startActivity(intent)
            }
            else -> {
//                Toast.makeText(this, "plz check ur id and pass ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}