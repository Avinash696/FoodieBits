package com.example.zepto.Application

import android.app.Application
import com.example.test.AviInterface
import com.example.zepto.Repositary.Repositary
import com.example.zepto.database.CartDatabase
import com.example.zepto.db.RetrofitHelper

class application : Application() {
    lateinit var repositary: Repositary
    override fun onCreate() {
        initilizer()
        super.onCreate()
    }

    private fun initilizer() {
            val cartService = RetrofitHelper.getClient().create(AviInterface::class.java)
            val commonDatabase = CartDatabase.getDatabase(applicationContext)
            repositary = Repositary(cartService, commonDatabase)

    }
}