package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowCheckoutAdminBinding
import com.example.zepto.model.adminOrderMaster

import com.example.zepto.ui.activity.FaqsActivity

class adapterOrderMasterAdmin(
    private val arrayData: ArrayList<adminOrderMaster>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowCheckoutAdminBinding

    class CustomViewHolder(binding: RowCheckoutAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowCheckoutAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        //status +date
        binding.tvIdCheckout.text = data.id.toString()
        binding.tvAddressCheckout.text = data.address
        binding.tvPaymentTypeCheckout.text = data.paymentType
        binding.tvDateCheckout.text = data.date
        binding.tvPaymentStatusCheckout.text = data.paymentStatus.toString()
//        binding.tvPaymentTypeCheckout.setImageResource(data.img)


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