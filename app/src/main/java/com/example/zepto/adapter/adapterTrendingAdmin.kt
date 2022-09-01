package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardTrendingAdminBinding
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.model.cardItemModel

class adapterTrendingAdmin(

    private val arrayData: ArrayList<cardItemModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: CardTrendingAdminBinding

    class CustomViewHolder(binding: CardTrendingAdminBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = CardTrendingAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
       binding.tvItemName.text = data.name
        binding.ivItemPic.setImageResource(data.img)
        binding.tvItemDiscountedCost.text = data.discount.toString()
        binding.tvItemWeight.text = data.discount.toString()
        binding.tvItemFakeCost.text = data.discountPrice.toString()

        //
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}