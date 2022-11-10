package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.RowBrandsAdminBinding
import com.example.zepto.model.brandModel


class adpterBannerAdmin(val context: Context, val arrayList: ArrayList<brandModel>) :
    RecyclerView.Adapter<adpterBannerAdmin.CustomBannerViewModel>() {
    private lateinit var binding: RowBrandsAdminBinding

    class CustomBannerViewModel(binding: RowBrandsAdminBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomBannerViewModel {
        binding =
            RowBrandsAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomBannerViewModel(binding)
    }

    override fun onBindViewHolder(holder:CustomBannerViewModel, position: Int) {
        val data = arrayList[position]
        binding.ivAdminBanner.setImageResource(R.drawable.gucci)
        binding.tvAdminBrandName.text = data.brandName
        binding.tvBrandAdminId.text = data.brandId.toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}