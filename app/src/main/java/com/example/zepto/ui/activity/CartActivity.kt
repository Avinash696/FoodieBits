package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.R
import com.example.zepto.adapter.adapterOrderList
import com.example.zepto.databinding.ActivityCartBinding
import com.example.zepto.model.orderListModel

class CartActivity : AppCompatActivity() {
    lateinit var intentTrending: Intent
    private lateinit var nameArray: ArrayList<String>
    private lateinit var amountArray: ArrayList<Int>
    private lateinit var imageArray: ArrayList<String>
    private lateinit var binding: ActivityCartBinding
    private var totalAmount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        intentDataReceive()
        populatingIntentData()
        totalAmount()

        //send to payment
        binding.btPayAmount.setOnClickListener {
            //why u want to add that ordered product and who will see
            startActivity(Intent(this, YourOrderActivity::class.java))
        }
    }

    private fun totalAmount() {
        for (rs in amountArray) {
            totalAmount += rs
        }

        binding.tvTotalAmount.text = totalAmount.toString()
    }

    private fun intentDataReceive() {
        intentTrending = intent
        //cart data
        nameArray = intentTrending.getStringArrayListExtra("nameArray")!!
        amountArray = intentTrending.getIntegerArrayListExtra("amountArray")!!
        imageArray = intentTrending.getStringArrayListExtra("imgArray")!!

        Log.d("cartArr", "onCreate: $nameArray ${nameArray.size} $amountArray $imageArray")

        for(i in 0 until imageArray.size){
            Log.d("onetime", "intentDataReceive: ${imageArray[i]}")
        }
    }

    private fun populatingIntentData() {
        val arrayList = ArrayList<orderListModel>()
//        arrayList.add(orderListModel("Delivered","20 Feb","289.00",R.drawable.order,"Chakki Atta",1)

        for (nus in 1..(nameArray.size)) {
            Log.d("loopCheck", "populatingIntentData: $nus")
            arrayList.add(
                orderListModel(
                    "Delivered",
                    "20 Feb",
                    amountArray[nus - 1].toString(),
                    imageArray[nus - 1],
                    nameArray[nus - 1],
                    1
                )
            )
        }

        binding.rvOrderedList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val arrayAdapter = adapterOrderList(arrayList, this)
        binding.rvOrderedList.adapter = arrayAdapter
    }
}