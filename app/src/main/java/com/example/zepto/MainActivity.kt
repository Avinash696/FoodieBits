package com.example.zepto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.zepto.R.layout.activity_main
import com.example.zepto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private fun authenticateUser(){
        val stName = binding.etName.text
        val stPass = binding.etPassword.text

        if((stName.equals("bpind")) && stPass.equals("bpind")){

        }
    }
}