package com.example.zepto.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.zepto.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        supportActionBar!!.title = "Detail Activity"
        val bundle = intent.extras
        val name = bundle!!.getBundle("myHomeKey")
        Log.d("rawat", "onCreate: ${name!!.get("nameKey")} ")



    }
    private fun changeFragment( fragment :Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flDetail, fragment)
        fragmentTransaction.commit()
    }

}