package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityYourOrderBinding

class YourOrderActivity : AppCompatActivity() {
    private lateinit var binding:ActivityYourOrderBinding
    private var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_your_order)

        binding.btPlaceOrder.setOnClickListener {
            startActivity(Intent(this,YourOrderStatusActivity::class.java))
        }


        //
        binding.ivAdd.setOnClickListener {
            binding.tvItemCountOrder.text = (count++).toString()
            binding.tvTotalCountOrder.text = (count * 29).toString()
        }
        binding.ivMinus.setOnClickListener {
            binding.tvItemCountOrder.text = (count--).toString()
            binding.tvTotalCountOrder.text = (count * 29).toString()
        }
    }
}