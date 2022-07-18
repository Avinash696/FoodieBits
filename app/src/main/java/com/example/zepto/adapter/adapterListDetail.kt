package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zepto.R
import com.example.zepto.model.listCategory

class adapterListDetail(
    val context: Context,
    val arraydata: ArrayList<listCategory>,
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return arraydata.size
    }

    override fun getItem(p0: Int): Any? {
        return arraydata[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var currentItemView = convertView
        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.detail_row, parent, false)
        }
        //get position
        val dataPos = arraydata[position]

        val iv = currentItemView!!.findViewById<ImageView>(R.id.ivRowDetail)
        val tv = currentItemView.findViewById<TextView>(R.id.tvRowDetail)

        //data
        iv.setImageResource(dataPos.imgCategory)
        tv.text = dataPos.name
        return currentItemView
    }


}