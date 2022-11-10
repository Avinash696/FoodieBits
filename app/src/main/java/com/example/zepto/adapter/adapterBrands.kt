package com.example.zepto.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.RowBrandsAdminBinding
import com.example.zepto.model.brandModel

class adapterBrands(val context: Context, val arrayList: ArrayList<brandModel>) :
    RecyclerView.Adapter<adapterBrands.CustomBrandsViewModel>(), Filterable {
    private lateinit var binding: RowBrandsAdminBinding

    val arrayDataFull = ArrayList(arrayList)

    class CustomBrandsViewModel(binding: RowBrandsAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomBrandsViewModel {
        binding =
            RowBrandsAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomBrandsViewModel(binding)
    }

    override fun onBindViewHolder(holder: CustomBrandsViewModel, position: Int) {
        val data = arrayList[position]
        binding.ivAdminBanner.setImageResource(R.drawable.gucci)
        binding.tvAdminBrandName.text = data.brandName
        binding.tvBrandAdminId.text = data.brandId.toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filteredList = ArrayList<brandModel>()
                if (p0 == null || p0.isEmpty()) {
                    filteredList.addAll(arrayDataFull)
                } else {
                    val pattern = p0.toString().trim().lowercase()
                    for (item in arrayList) {
                        if (item.brandId.toString().trim().contains(pattern)) {
                            Log.d("hujhhj", "performFiltering:${pattern}  ${item.brandId}  ")
                            filteredList.add(item)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredList
                return result
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                arrayList.clear()
                arrayList.addAll(p1?.values as Collection<brandModel>)
                notifyDataSetChanged()
            }

        }
    }
}