package com.example.zepto.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.RowOrderListBinding
import com.example.zepto.model.cartCommonModel
import com.example.zepto.module.Toasty
import com.squareup.picasso.Picasso

class adapterOrderList(
//    private val arrayData: ArrayList<orderListModel>,
    private val arrayData: ArrayList<cartCommonModel>?,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: RowOrderListBinding

    class CustomViewHolder(binding: RowOrderListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowOrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData?.get(position)

        binding.tvItemNameDelived.text = data!!.name
//        binding.tvItemQuantity.text = count.toString()
        binding.tvDeliverdAmount.text = (data.Price).toString()
        binding.tvItemQuantity.text = "1"
        Picasso.get().load((data.img).trim())
            .resize(30, 30)
            .placeholder(R.drawable.all_item)
            .error(R.drawable.error)
            .into(binding.ivItemDelivered)

        binding.btnAdd.setOnClickListener {
            //                                                position count  amount
            Log.d(
                "adapterOrder",
//                "onBindViewHolder: $position  ${binding.tvItemQuantity.text} ${data.Price}"
                "onBindViewHolder: $position  ${Integer.parseInt(holder.itemView.findViewById<TextView>(R.id.tvItemQuantity).text.toString()) +1 } ${data.Price}"
            )
//          binding.tvItemQuantity.text =  (Integer.parseInt(binding.tvItemQuantity.text.toString()) +1 ).toString()
            holder.itemView.findViewById<TextView>(R.id.tvItemQuantity).text =  (Integer.parseInt(binding.tvItemQuantity.text.toString()) +1 ).toString()
        }
        binding.tvDeliverdAmount.setOnClickListener {
            Log.d(
                "adapterOrder",
                "Amount : ${Integer.parseInt(binding.tvDeliverdAmount.text.toString()) * (Integer.parseInt(binding.tvItemQuantity.text.toString()) +1 )}")
        }
    }

    override fun getItemCount(): Int {
        return arrayData?.size ?: 0
    }

    private fun getCount(): Int {
        var count = 1

        return count
    }
}