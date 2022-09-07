package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardTrendingAdminBinding
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardItemWithoutId
import com.squareup.picasso.Picasso

class adapterTrendingAdmin(

    private val arrayData: ArrayList<SubCategoryImgX>,
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
       binding.tvItemName.text = data.productName
        Picasso.get().load(data.productImg).into(binding.ivItemPic)
//        binding.ivItemPic.setImageResource(data)
        binding.tvItemDiscountedCost.text = data.discountedPrice
        binding.tvItemWeight.text = data.discountedPrice
        binding.tvItemFakeCost.text = data.priceShow

        //
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}