package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import org.w3c.dom.Text

class adapterCustomRv(val context: Context, val arrayData: ArrayList<customFilterModel>) :
    RecyclerView.Adapter<adapterCustomRv.CustomViewHolder>(), Filterable {

    val arrayDataFull = ArrayList(arrayData)

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.image_view)
        val name = itemView.findViewById<TextView>(R.id.textView1)
        val desc = itemView.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_filter_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = arrayData[position]
        holder.img.setImageResource(data.img)
        holder.desc.text = data.desc
        holder.name.text = data.name
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constrain: CharSequence?): FilterResults {   //background
                var filteredList = arrayListOf<customFilterModel>()
                if (constrain == null || constrain.length == 0) {
//                    filteredList.addAll(arrayData)
                    filteredList.addAll(arrayDataFull)
                } else {
                    val pattern = constrain.toString().lowercase().trim()
                    for (item in arrayData) {
                        if (item.name.lowercase().contains(pattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredList
                return result
            }

            override fun publishResults(constrain: CharSequence?, result: FilterResults?) {   //main
                //main list empty
                arrayData.clear()
                arrayData.addAll(result!!.values as Collection<customFilterModel>)
                notifyDataSetChanged()
            }
        }
    }
}