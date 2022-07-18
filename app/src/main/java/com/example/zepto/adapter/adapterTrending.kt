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
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.YourOrderActivity

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
        binding.ivItemPic.setImageResource(data.img)

        //onclick on item
        holder.itemView.setOnClickListener{
            val intent = Intent(context,SingleTrendingActivity::class.java)
            Log.d("ttt", "onBindViewHolder:${data.Price} ${data.discount}  ${data.discountPrice}")
            intent.putExtra("amountKey",data.Price)
            intent.putExtra("nameKey",data.name)
            intent.putExtra("imgKey",data.img)
            context.startActivity(intent)
//            context.startActivity(Intent(context,SingleTrendingActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}