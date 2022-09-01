package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.OrderSummaryActivity
import com.example.zepto.ui.activity.SingleTrendingActivity

class adapterOrderList(
    private val arrayData: ArrayList<orderListModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    lateinit var binding :OrderHistoryBinding
    lateinit var binding: RowOrderListBinding

    class CustomViewHolder(binding: RowOrderListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowOrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvDeliverdDate.text = data.itemStatus+","+data.itemDate
        binding.tvDeliverdAmount.text = data.itemAmount
        binding.tvItemNameDelived.text = data.itemName
        binding.ivItemDelivered.setImageResource(data.itemImage)
        binding.tvOrderQuantity.text = "Order Total" +"("+data.itemQuantity+")"

        //onclick on item
        holder.itemView.setOnClickListener{
            context.startActivity(Intent(context, SingleTrendingActivity::class.java))
        }
        //btn click
//        binding
//        binding.btNeedHelp.setOnClickListener {
//            context.startActivity(Intent(context,FaqsActivity::class.java))
//        }
//        binding.btViewDetail.setOnClickListener {
//            context.startActivity(Intent(context,OrderSummaryActivity::class.java))
//        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}