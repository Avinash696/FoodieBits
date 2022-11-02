package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zepto.R
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.*
import com.example.zepto.module.Toasty
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.OrderSummaryActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.squareup.picasso.Picasso

class adapterOrderList(
//    private val arrayData: ArrayList<orderListModel>,
    private val arrayData: ArrayList<cartCommonModel>?,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var count = 1
    lateinit var binding: RowOrderListBinding

    class CustomViewHolder(binding: RowOrderListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowOrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData?.get(position)

        binding.tvItemNameDelived.text = data!!.name
        binding.tvItemQuantity.text = count.toString()
        binding.tvItemQuantity.text = "₹" + (data.Price)
        Picasso.get().load((data.img).trim())
            .resize(30, 30)
            .placeholder(R.drawable.all_item)
            .error(R.drawable.error)
            .into(binding.ivItemDelivered)

        binding.btnAdd.setOnClickListener {
            count += 1
            binding.tvDeliverdAmount.text = "₹" + (Integer.parseInt(data.Price.toString())* count)
            binding.tvItemQuantity.text = (count).toString()
        }
        binding.btnSub.setOnClickListener {
            count -= 1
            binding.tvDeliverdAmount.text = "₹" + (Integer.parseInt(data.Price.toString())* count)
            binding.tvItemQuantity.text = (count).toString()
        }
    }

    override fun getItemCount(): Int {
        return arrayData?.size ?: 0
    }
}