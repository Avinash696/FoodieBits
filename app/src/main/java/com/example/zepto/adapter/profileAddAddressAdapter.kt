package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowProfileCartBinding
import com.example.zepto.model.profileSavedAddressModel
import com.example.zepto.ui.activity.AddDeliveryAddressActivity

class profileAddAddressAdapter(
    val context: Context,
    val arrayData: ArrayList<profileSavedAddressModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RowProfileCartBinding

    class CustomViewHolder(binding: RowProfileCartBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowProfileCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        binding.tvHomeRowProfile.text = data.type
        binding.tvHomeRowProfileDesc.text = data.fullAddress
        binding.ivAddEditRowProfile.setOnClickListener{
            Toast.makeText(context, "Edit Update Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,AddDeliveryAddressActivity::class.java)
//            intent.putExtra("")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return arrayData.size
    }


}


