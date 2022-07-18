package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.ui.activity.DetailActivity


class adapterCategories(
    private val context: Context,
    private val arraydata: ArrayList<cardItemModel>,
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

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
//        var  view = View.inflate(context,R.layout.activity_list_item,null)
//
//        var name :String= view.findViewById(R.id.na)
        var myView = convertView
        if (myView == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView =  inflate.inflate(R.layout.catagories_row,viewGroup,false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvCateItemName)
        val itemPic = myView.findViewById<ImageView>(R.id.ivCateItemPic)

        val data = arraydata[position]

        //set data
        name.text =data.name
        itemPic.setImageResource(data.img)

        itemPic.setOnClickListener {
            context.startActivity(Intent(context,DetailActivity::class.java))
        }
        return myView
    }

}