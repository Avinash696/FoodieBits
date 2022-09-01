package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.Admin.ui.activity.DisplayRetailerAddUserActivity
import com.example.zepto.databinding.RowRetailerAdduserBinding
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModelItem
import com.example.zepto.module.retailerAddUserModel
import com.example.zepto.module.retrailerFirstAddUserModel
import com.squareup.picasso.Picasso


class adapterRetailerAddUser(

    private val arrayData: retailerResponceAddUserModel?,
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
        val data = arrayData!![position]
        //status +date
//        binding.ivNameRetailerAddUser.setImageResource(data.pic)
        Log.d("Personaladapter", "onBindViewHolder: ${data.adminImgPic}")
        val imgPath = "https://images.app.goo.gl/iVYAFr3DqthNR7gq5"
        Picasso.get().load("http://56testing.club/imgFolder/uploads/admins/admin1.jpg").into(binding.ivNameRetailerAddUser);
//        binding.ivNameRetailerAddUser.setImageBitmap(data.pic)
        binding.tvNameRetailerAddUser.text = data.name
        binding.tvNameRetailerId.text = "1"

        //onclick on item
        holder.itemView.setOnClickListener {

            Toast.makeText(context, "U clicked ${holder.adapterPosition}", Toast.LENGTH_SHORT).show()
            val intentData = Intent(context,DisplayRetailerAddUserActivity::class.java)
            intentData.putExtra("addressKey",data.address)
            intentData.putExtra("passwordKey",data.password)
            intentData.putExtra("adharKey",data.adhar)
            intentData.putExtra("adminImgPicKey",data.adminImgPic)
            intentData.putExtra("emailKey",data.email)
            intentData.putExtra("idKey",data.id)
            intentData.putExtra("mobileNoKey",data.mobileNo)
            intentData.putExtra("nameKey",data.name)
            intentData.putExtra("panCardKey",data.panCard)
            intentData.putExtra("roleKey",data.role)
            intentData.putExtra("shopReqNoKey",data.shopReqNo)

            context.startActivity(intentData)
        }
    }

    override fun getItemCount(): Int {
        return arrayData!!.size
    }

}
