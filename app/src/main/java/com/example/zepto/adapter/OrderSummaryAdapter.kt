package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.squareup.picasso.Picasso

class OrderSummaryAdapter (
    private val arrayData: ArrayList<orderListModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding : OrderHistoryBinding

    class CustomViewHolder(binding: OrderHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = OrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvDeliverdDate.text = data.itemStatus+","+data.itemDate
        binding.tvDeliverdAmount.text = data.itemAmount
        binding.tvItemNameDelived.text = data.itemName
        Picasso.get().load(data.itemImage).into(binding.ivItemDelivered)
//        binding.ivItemDelivered.setImageResource(data.itemImage)
        binding.tvOrderQuantity.text = "Order Total" +"("+data.itemQuantity+")"

        //onclick on item
        holder.itemView.setOnClickListener{
            context.startActivity(Intent(context, SingleTrendingActivity::class.java))
        }
        //btn click
        binding.btNeedHelp.setOnClickListener {
            context.startActivity(Intent(context, FaqsActivity::class.java))
        }
        binding.btViewDetail.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}