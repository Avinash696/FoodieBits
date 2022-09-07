package com.example.zepto.Application

import android.app.Application
import android.content.Context
import com.example.test.aviInterface
import com.example.zepto.db.RetrofitHelper
import retrofit2.Retrofit

class applicationTest :Application() {
    lateinit var   client :aviInterface
    override fun onCreate() {
        initilizer()
        super.onCreate()
    }

    private fun initilizer() {
         client = RetrofitHelper.getClient().create(aviInterface::class.java)
    }
}