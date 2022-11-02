package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.AviInterface
import com.example.zepto.database.cartResult
import com.example.zepto.databinding.CardviewItemBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.module.Toasty
import com.example.zepto.viewModel.ItemCountViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

class adapterTrending(
    private val arrayData: List<SubCategoryImgX>,
    private val context: Context,
    private var countViewModel: ItemCountViewModel
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: CardviewItemBinding
    private var cartName: ArrayList<String> = ArrayList()
    private var cartAmount: ArrayList<Int> = ArrayList()
    private var cartImage: ArrayList<String> = ArrayList()
    private lateinit var cartItem: SubCategoryImgX ;
    private var count = 1

    class CustomViewHolder(binding: CardviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]

        binding.tvItemName.text = data.productName
        binding.tvItemFakeCost.text = data.discountedPrice
        binding.tvItemDiscountedCost.text = data.priceShow
        Picasso.get().load(data.productImg).into(binding.ivItemPic)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, data.productName, Toast.LENGTH_SHORT).show()
        }
        //add item
        binding.ivAddBtn.setOnClickListener {
//                countViewModel.setTrendingItem(data)
//                  countViewModel.setCartItem(data)
//                countViewModel.setCartItem(data)
            countViewModel.setCartItem(data)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    private fun updateCartQuantity(userIdItem: Int, quantityItem: Int) {
        val updateId = RequestBody.create(MediaType.parse("text"), userIdItem.toString())
        val quantity = RequestBody.create(MediaType.parse("text"), quantityItem.toString())
        val reto = RetrofitHelper.getClient().create(AviInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = reto.updateCartQuantity(updateId, quantity)
            if (call.isSuccessful)
                Toasty.getToasty(context, "${call.body()!!.message}")
            else
                Toasty.getToasty(context, "${call.errorBody()}")
        }
    }
}