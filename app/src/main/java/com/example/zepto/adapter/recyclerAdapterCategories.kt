package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.databinding.RowCategoriesAdminBinding
import com.example.zepto.model.AdminCategoriesModel
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.SubListSubAdminActivity

class recyclerAdapterCategories(
    private val arrayData: ArrayList<AdminCategoriesModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowCategoriesAdminBinding

    class CustomViewHolder(binding: RowCategoriesAdminBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowCategoriesAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvAdminId.text = data.id.toString()
        binding.tvAdminCatName.text = data.name
        binding.ivAdminCategory.setImageResource(data.img)



        //onclick on item
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, FaqsActivity::class.java))
        }
        //item  with id will added
        binding.btSubAdminSubCat.setOnClickListener {
            val intent = Intent(context,SubListSubAdminActivity::class.java)
            intent.putExtra("SubCatKey",data.name)
            context.startActivity(intent)
//            context.startActivity(Intent(context,SubListSubAdminActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}