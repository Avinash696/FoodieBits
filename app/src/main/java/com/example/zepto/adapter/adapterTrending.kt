package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.model.cardItemModel
import com.example.zepto.module.cartItemLib
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.viewModel.ItemCountViewModel
import com.squareup.picasso.Picasso

class adapterTrending(
    private val arrayData: ArrayList<cardItemModel>,
    private val context: Context,
    private var countViewModel: ItemCountViewModel
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: CardviewItemBinding
   private var cartName: ArrayList<String> = ArrayList()
   private var cartAmount: ArrayList<Int> = ArrayList()
   private var cartImage: ArrayList<String> = ArrayList()

    class CustomViewHolder(binding: CardviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        binding.tvItemName.text = data.name
        Picasso.get().load(data.img).into(binding.ivItemPic)
//        binding.ivItemPic.setImageResource(data.img)
        //onclick on item
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${data.name}", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, SingleTrendingActivity::class.java)
////            Log.d("ttt", "onBindViewHolder:${data.Price} ${data.discount}  ${data.discountPrice}")
//            intent.putExtra("amountKey", data.Price)
//            intent.putExtra("nameKey", data.name)
//            intent.putExtra("imgKey", data.img)
//            //testing onclick itemView
//            intent.putExtra("nameArray", cartName)
//            intent.putExtra("amountArray", cartAmount)
//            intent.putExtra("imageArray", cartImage)
//            context.startActivity(intent)
//            context.startActivity(Intent(context,SingleTrendingActivity::class.java))
        }

        //add item
        binding.ivAddBtn.setOnClickListener {
            countViewModel.setTrendingItem(data)
//            Log.d("nameArra", "onBindViewHolder:${data.img}")
//            Toast.makeText(context, "Item Added" + data.img, Toast.LENGTH_SHORT).show()

//            cartName.add(data.name)
//            cartAmount.add(data.Price)
//            cartImage.add(data.img)
//
////            Log.d("justdd", "onBindViewHolder:${countViewMode.count} ")
//            Log.d("amountCheckModule", "onBindViewHolder:${cartName}  $cartAmount $cartImage")
//            cartItemLib(cartName,cartAmount,cartImage)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}