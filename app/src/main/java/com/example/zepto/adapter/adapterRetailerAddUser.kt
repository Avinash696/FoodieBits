package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.Admin.ui.activity.DisplayRetailerAddUserActivity
import com.example.zepto.databinding.RowRetailerAdduserBinding
import com.example.zepto.module.retrailerFirstAddUserModel

class adapterRetailerAddUser(

    private val arrayData: ArrayList<retrailerFirstAddUserModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowRetailerAdduserBinding

    class CustomViewHolder(binding: RowRetailerAdduserBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowRetailerAdduserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date

        binding.ivNameRetailerAddUser.setImageResource(data.pic)
//        binding.ivNameRetailerAddUser.setImageBitmap(data.pic)
        binding.tvNameRetailerAddUser.text = data.name
        binding.tvNameRetailerId.text = "1"

        //onclick on item
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "U clicked", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, DisplayRetailerAddUserActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
}
