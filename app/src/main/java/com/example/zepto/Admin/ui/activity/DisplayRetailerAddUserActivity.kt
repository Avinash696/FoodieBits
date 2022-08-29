package com.example.zepto.Admin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zepto.R

class DisplayRetailerAddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_retailer_add_user)

        supportActionBar!!.title = "All Detail "
    }
}