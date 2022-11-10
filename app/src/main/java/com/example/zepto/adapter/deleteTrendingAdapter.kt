package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.ui.activity.SingleTrendingActivity

class deleteTrendingAdapter(
    private val arrayData: ArrayList<cardItemWithoutId>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: CardviewItemBinding
    var cartName: ArrayList<String> = ArrayList()
    var cartAmount: ArrayList<Int> = ArrayList()
    var cartImage: ArrayList<Int> = ArrayList()

    class CustomViewHolder(binding: CardviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        binding.tvItemName.text = data.name
        binding.ivItemPic.setImageResource(data.img)
        //onclick on item
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleTrendingActivity::class.java)
            Log.d("ttt", "onBindViewHolder:${data.Price} ${data.itemCount}  ${data.itemCount}")
            intent.putExtra("amountKey", data.Price)
            intent.putExtra("nameKey", data.name)
            intent.putExtra("imgKey", data.img)
            //testing onclick itemView
            intent.putExtra("nameArray", cartName)
            intent.putExtra("amountArray", cartAmount)
            intent.putExtra("imageArray", cartImage)
            context.startActivity(intent)
//            context.startActivity(Intent(context,SingleTrendingActivity::class.java))
        }

        //add item
        binding.ivAddBtn.setOnClickListener {
            Log.d("nameArra", "onBindViewHolder:${data.img}")
            Toast.makeText(context, "Item Added" + data.img, Toast.LENGTH_SHORT).show()

            cartName.add(data.name)
            cartAmount.add(data.Price)
            cartImage.add(data.img)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}