package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.model.cardItemModel
import com.example.zepto.ui.activity.DetailActivity

class adapterTrending(
    private val arrayData: ArrayList<cardItemModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding :CardviewItemBinding

    class CustomViewHolder(binding: CardviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         binding = CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        binding.tvItemName.text = data.name

        //onclick on item
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            val intent = Intent(context,DetailActivity::class.java)
            bundle.putString("nameKey",data.name)
            bundle.putString("discountKey",data.discount.toString())
            bundle.putString("discountPriceKey",data.discountPrice.toString())
            bundle.putString("priceKey",data.Price.toString())
            bundle.putString("imgKey",data.img.toString())
            intent.putExtra("myHomeKey",bundle)
            Log.d("rawat", "onBindViewHolder: ${data.name}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}