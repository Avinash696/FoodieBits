package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityOrderSummaryBinding
import com.example.zepto.model.summayModel

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_summary)
//        populatingData()
        binding.btReorder.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.tvHelpSummary.setOnClickListener {
            startActivity(Intent(this,FaqsActivity::class.java))
        }
        binding.tvMoreItem.setOnClickListener {
            Toast.makeText(this, "Work in Prgress", Toast.LENGTH_SHORT).show()
        }
    }
//
//    private fun populatingData() {
//        val array = ArrayList<summayModel>()
//        array.add(summayModel("20 Feb", "Snowy", "23FDYKS24", 2, "234"))
//        array.add(summayModel("20 Feb", "Jonathan", "23FDYKS24", 1, "234"))
//        array.add(summayModel("20 Feb", "Lemon", "23FDYKS24", 1, "234"))
//        array.add(summayModel("20 Feb", "DrDisrespect", "23FDYKS24", 22, "234"))
//
//    }
}