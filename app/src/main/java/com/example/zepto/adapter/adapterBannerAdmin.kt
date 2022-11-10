package com.example.zepto.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.databinding.RowBannerAdminBinding
import com.example.zepto.model.BannerAdminModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class adapterOfferBannerAdmin(val arrayData: ArrayList<BannerAdminModel>, val context: Context) :
    RecyclerView.Adapter<adapterOfferBannerAdmin.CustomViewModelBanner>(), Filterable {
    lateinit var binding: RowBannerAdminBinding

    //    var exampleListFull = ArrayList<BannerAdminModel>(arrayList)
    val arrayDataFull = ArrayList(arrayData)

    class CustomViewModelBanner(binding: RowBannerAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewModelBanner {
        binding = RowBannerAdminBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewModelBanner(binding)
    }

    override fun onBindViewHolder(holder: CustomViewModelBanner, position: Int) {

        val data = arrayData[position]

        binding.ivCart.setImageResource(data.img)
        binding.tvDateBanner.text = data.date
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = arrayListOf<BannerAdminModel>()
                if (charSequence == null || charSequence.isEmpty()) {
                    filteredList.addAll(arrayDataFull)
                }
                else {
                    val pattern = charSequence.toString().trim().lowercase().toString()
                    for (item in arrayData) {
                        if (item.date.trim().lowercase().toString().contains(pattern)) {
//                        if (item.date.trim().lowercase().startsWith(pattern)) {
                            Log.d("anyfgd", "performFiltering: ${item.date.trim().lowercase()}   $pattern")
                            filteredList.add(item)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredList
                return result
            }

            override fun publishResults(charSequence: CharSequence?, p1: FilterResults?) {
                arrayData.clear()
                arrayData.addAll(p1!!.values as Collection<BannerAdminModel>)
                notifyDataSetChanged()
            }
        }
    }
}