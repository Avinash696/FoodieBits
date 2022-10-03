package com.example.zepto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.R
import com.example.zepto.adapter.adapterOrderList
import com.example.zepto.databinding.ActivityCartBinding
import com.example.zepto.model.orderListModel
import com.example.zepto.module.cartItemLib
import com.example.zepto.viewModel.CartViewModel

class CartActivity : AppCompatActivity() {
    lateinit var intentTrending: Intent
    lateinit var intentCategory: Intent
    private lateinit var nameArray: ArrayList<String>
    private lateinit var amountArray: ArrayList<Int>
    private lateinit var imageArray: ArrayList<String>

    private lateinit var cardViewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    private var totalAmount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        cardViewModel = ViewModelProvider(this)[CartViewModel::class.java]
//        intentDataReceive()
        intentTrending = intent
        //cart data

        nameArray = intentTrending.getStringArrayListExtra("nameArray")!!
        amountArray = intentTrending.getIntegerArrayListExtra("amountArray")!!
        imageArray = intentTrending.getStringArrayListExtra("imgArray")!!
        cardViewModel.setArray(nameArray, amountArray, imageArray)
        Log.d("cartArr", "onCreate: $nameArray ${nameArray.size} $amountArray $imageArray")
        populatingIntentData()
        totalAmount()


        //send to payment
        binding.btPayAmount.setOnClickListener {
            //why u want to add that ordered product and who will see
            val intent = Intent(this, YourOrderActivity::class.java)

            intent.putExtra("nameArray", nameArray)
            intent.putExtra("amountArray", amountArray)
            intent.putExtra("imgArray", imageArray)
            Log.d("cartArrSend", "onCreate: ${cardViewModel.cartName}  ${cardViewModel.cartAmount} ${cardViewModel.cartImage}")
            startActivity(intent)
        }
    }

    private fun totalAmount() {
        for (rs in amountArray) {
            totalAmount += rs
        }

        binding.tvTotalAmount.text = totalAmount.toString()
    }

    private fun intentDataReceive() {


    }

    private fun populatingIntentData() {
        val arrayList = ArrayList<orderListModel>()

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