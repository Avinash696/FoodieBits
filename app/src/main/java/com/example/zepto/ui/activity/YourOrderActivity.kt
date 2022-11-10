package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.test.AviInterface
import com.example.zepto.R
import com.example.zepto.databinding.ActivityYourOrderBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import kotlin.random.Random

class YourOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourOrderBinding
    private var count: Int = 0
    lateinit var intentOrder:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_your_order)

        //intent get data
         intentOrder = intent

      val  nameArray = intentOrder.getStringArrayListExtra("nameArray")!!
      val  amountArray = intentOrder.getIntegerArrayListExtra("amountArray")!!
      val  imageArray = intentOrder.getStringArrayListExtra("imgArray")

        Log.d("yourOrder", "onCreate: $nameArray $amountArray $imageArray")


        binding.btPlaceOrder.setOnClickListener {
            postPaymentSlip()
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

    private fun postPaymentSlip(){
        val rand = (0..1000).random()

        val item = ResponseBody.create("text/plain".toMediaTypeOrNull(), "biscuit$rand")
        val type = ResponseBody.create("text/plain".toMediaTypeOrNull(), "COD$rand")
        val retro = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch {
            val call = retro.postOrderedPlaced(item,type)
            if(call.isSuccessful)
                tosty("Order Placed ")
            else
                tosty("Order Placed ${call.errorBody()}")
        }
    }
    private fun tosty(message:String){
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }
}