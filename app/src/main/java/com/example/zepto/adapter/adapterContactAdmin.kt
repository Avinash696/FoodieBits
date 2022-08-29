package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowAdminContactBinding
import com.example.zepto.databinding.RowCheckoutAdminBinding
import com.example.zepto.model.adminContactModel
import com.example.zepto.model.adminOrderMaster
import com.example.zepto.ui.activity.FaqsActivity

class adapterContactAdmin(

    private val arrayData: ArrayList<adminContactModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowAdminContactBinding

    class CustomViewHolder(binding: RowAdminContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowAdminContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvIdContact.text = data.id.toString()
        binding.tvNameContact.text = data.name
        binding.tvEmailContact.text = data.email
        binding.tvMobileNoContact.text = data.number


        //onclick on item
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, FaqsActivity::class.java))
        }
        //btn click
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}
