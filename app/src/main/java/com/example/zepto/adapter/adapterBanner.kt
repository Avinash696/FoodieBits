package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowBannerBrandBinding
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.bannerBrandModel
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.TopBannerActivity

class adapterBanner(
    private val arrayData: ArrayList<bannerBrandModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding : RowBannerBrandBinding

    class CustomViewHolder(binding: RowBannerBrandBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowBannerBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvBrandInFocusRow.setImageResource(data.bannerImg)

        //onclick on item
        holder.itemView.setOnClickListener{
            context.startActivity(Intent(context, TopBannerActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}