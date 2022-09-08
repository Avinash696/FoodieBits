package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.databinding.RowCategoriesAdminBinding
import com.example.zepto.model.AdminCategoriesModel
import com.example.zepto.model.CategoryImg
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.SubListSubAdminActivity
import com.squareup.picasso.Picasso

class recyclerAdapterCategories(
    private val arrayData: ArrayList<CategoryImg>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowCategoriesAdminBinding

    class CustomViewHolder(binding: RowCategoriesAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowCategoriesAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        if(data.categoryStatus == 1 ){
            binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
        }
        else {
            binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
        }
        //status +date
        Log.d("flow", "onBindViewHolder: $data")
        binding.tvAdminId.text = data.id.toString()
        binding.tvAdminCatName.text = data.categoryName
//        binding.ivAdminCategory.setImageResource(data.img)
        Picasso.get()
            .load(data.categoryImg)
            .into(binding.ivAdminCategory)

        //onclick on item
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, FaqsActivity::class.java))
        }
        //item  with id will added
        binding.btSubAdminSubCat.setOnClickListener {
            val intent = Intent(context, SubListSubAdminActivity::class.java)
            intent.putExtra("SubCatKey", data.categoryName)
            intent.putExtra("SubCatIdKey", data.id)
            Log.d("flow", "onBindViewHolder: ${data.categoryName} ${data.id}")
            context.startActivity(intent)
        }
        //active set
        binding.btCardMainCategoryActive.setOnClickListener {

            if (data.categoryStatus == 0) {
                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
                binding.btCardMainCategoryActive.text = "Active"
                //update status id

            } else {
                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
                binding.btCardMainCategoryActive.text = "Deactivate"
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}