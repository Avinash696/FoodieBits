package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityYourOrderBinding

class YourOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourOrderBinding
    private var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_your_order)

        //intent get data
        val intent = intent
        val nameIntent = intent.getStringExtra("nameKey")
        val countIntent = intent.getIntExtra("itemCountKey", 0)
        val amountIntent = intent.getIntExtra("amountKey", 0)
        if (nameIntent != null) {
            setIntentData(nameIntent, countIntent, amountIntent)
        }
        Log.d("sunday", "onCreate: $$nameIntent  $countIntent $amountIntent")
        binding.btPlaceOrder.setOnClickListener {
            startActivity(Intent(this, YourOrderStatusActivity::class.java))
        }

        binding.ivAdd.setOnClickListener {
            binding.tvItemCountOrder.text = (count++).toString()
            binding.tvTotalCountOrder.text = (count * 29).toString()
        }
        binding.ivMinus.setOnClickListener {
            binding.tvItemCountOrder.text = (count--).toString()
            binding.tvTotalCountOrder.text = (count * 29).toString()
        }
    }

    private fun setIntentData(nameIntent: String, countIntent: Int, amountIntent: Int) {
        //  countIntent and amountIntent never null  == 0
        binding.tvItemName.text = nameIntent
        binding.tvItemCostYourOrder.text = amountIntent.toString()
        binding.tvItemCountOrder.text = countIntent.toString()
        //gst values
        binding.tvTotalOderGst.text = amountIntent.toString()
        //custom tax let it be (amount/4)
        //let amount be fix == 200 delivery
        binding.tvGrandTotal.text = "200"
        binding.tvTaxCharges.text = (amountIntent / 4).toString()
        val tax = (amountIntent / 4)
        binding.tvTotalCountOrder.text =( 200 + amountIntent).toString()

    }
}