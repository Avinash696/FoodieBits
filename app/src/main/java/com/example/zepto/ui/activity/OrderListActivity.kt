package com.example.zepto.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.R
import com.example.zepto.adapter.adapterOrderList
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.databinding.ActivityOrderListBinding
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.model.orderListModel

class OrderListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list)
        populatingOrdersList()
    }

    private fun populatingOrdersList() {

        val arrayList = ArrayList<orderListModel>()
//        arrayList.add(orderListModel("Delivered","20 Feb","289.00",R.drawable.order,"Chakki Atta",1)
        arrayList.add(
            orderListModel(
                "Delivered",
                "20 Feb",
                "289.00",
                R.drawable.order.toString(),
                "Chakki Atta",
                1
            )
        )
        arrayList.add(
            orderListModel(
                "Pending",
                "17 Feb",
                "200.00",
                R.drawable.order.toString(),
                "Maggi",
                2
            )
        )

        binding.rvOrderedList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val arrayAdapter = adapterOrderList(arrayList, this)
        binding.rvOrderedList.adapter = arrayAdapter
    }
}