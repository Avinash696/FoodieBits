package com.example.zepto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.R
import com.example.zepto.adapter.adapterOrderList
import com.example.zepto.databinding.ActivityCartBinding
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardResponceModel
import com.example.zepto.model.cartCommonModel
import com.example.zepto.viewModel.CartViewModel

class CartActivity : AppCompatActivity() {
    lateinit var intentTrending: Intent
    lateinit var intentCategory: Intent

    private lateinit var cardViewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    private var totalApiAmount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        cardViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        //current user intent
        val currentUserIntent = intent
        val urCartCurrentUser = currentUserIntent.getStringExtra("tempCurrentUser")
        val commonCartData =
            currentUserIntent.getParcelableArrayListExtra<cartCommonModel>("commonCartKey")
        Log.d("gfkjd", "Cart $commonCartData  ${commonCartData?.size}")
        populatingIntentData(commonCartData)
//        intentDataReceive()
        intentTrending = intent
//        val user = intentTrending.getParcelableExtra<SubCategoryImgX>("cartTrendingKey")
        val user = intentTrending.getParcelableArrayListExtra<SubCategoryImgX>("cartTrendingKey")

        Log.d("rainWeight", "cart: $user")
        //cart data
//        nameArray = intentTrending.getStringArrayListExtra("nameArray")!!
//        amountArray = intentTrending.getIntegerArrayListExtra("amountArray")!!
//        imageArray = intentTrending.getStringArrayListExtra("imgArray")!!
//        cardViewModel.setArray(nameArray, amountArray, imageArray)

        //api hit data post
        for (cart in cardViewModel.cartAmount)
            Log.d("cartCheck", "onCreate: $cart")
//        insertIntoCart(urCartCurrentUser.toString())                                // input field needed


        //send to payment
        binding.btPayAmount.setOnClickListener {
            //why u want to add that ordered product and who will see
            val intent = Intent(this, YourOrderActivity::class.java)

            Log.d(
                "cartArrSend",
                "onCreate: ${cardViewModel.cartName}  ${cardViewModel.cartAmount} ${cardViewModel.cartImage}"
            )
            startActivity(intent)
        }
        //change location  to profile and (use location manager there only)
        binding.cvLocationCart.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun totalAmount(body: cardResponceModel?) {
        for (i in 0 until body!!.categoryImg.size) {
            totalApiAmount += Integer.parseInt(body.categoryImg[i].cartPrice)
        }
        binding.tvTotalAmount.text = totalApiAmount.toString()
    }


    //    private fun populatingIntentData(body: cardResponceModel?) {
    private fun populatingIntentData(body: ArrayList<cartCommonModel>?) {

        binding.rvOrderedList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val arrayAdapter = adapterOrderList(body, this)
        binding.rvOrderedList.adapter = arrayAdapter
    }
//    private fun insertIntoCart(action :String){
//        Log.d("populatingData", "action: $action")
//        var reto = RetrofitHelper.getClient().create(AviInterface::class.java)
//        GlobalScope.launch(Dispatchers.Main) {
//            val call = reto.getCartDetail(action)
//            if(call.isSuccessful) {
//
//                populatingIntentData(call.body())
//                totalAmount(call.body())
//            }
//            else
//                Toasty.getToasty(applicationContext,"${call.errorBody()}")
//        }
//    }
}