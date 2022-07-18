package com.example.zepto.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityFaqsBinding


class FaqsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqsBinding
    private val paths = arrayOf("item 1", "https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list", "item 3")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faqs)

        //spinner set
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, paths
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spHowToRegister!!.adapter = adapter

    }
}