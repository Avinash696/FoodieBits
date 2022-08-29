package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowBannerBrandBinding
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.bannerBrandModel
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.DetailActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.TopBannerActivity

class adapterBanner(
    private val arrayData: ArrayList<bannerBrandModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowBannerBrandBinding

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
        holder.itemView.setOnClickListener {

        }
        holder.itemView.setOnClickListener {
            Log.d("checkAdapterPosition", "onBindViewHolder: ${holder.adapterPosition}")
            val intent = Intent(context, DetailActivity::class.java)
            when (holder.adapterPosition) {
                0 -> {
                    intent.putExtra("beautyKey", 2)
                    context.startActivity(intent)
                }
                1 -> {

                    intent.putExtra("beautyKey", 11)
                    context.startActivity(intent)
                }
                2 -> {
                    intent.putExtra("beautyKey", 1)
                    context.startActivity(intent)
                }
                3 -> {
                    intent.putExtra("beautyKey", 5)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}