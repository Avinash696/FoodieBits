package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.zepto.R
import com.example.zepto.model.cardItemModel


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
            myView =  inflate.inflate(R.layout.cardview_item,viewGroup,false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvItemName)
        val itemWeight = myView!!.findViewById<TextView>(R.id.tvItemWeight)
        val itemFakeCost = myView!!.findViewById<TextView>(R.id.tvItemFakeCost)
        val itemDiscountCost = myView!!.findViewById<TextView>(R.id.tvItemDiscountedCost)
        val itemPic = myView!!.findViewById<ImageView>(R.id.ivItemPic)

        val data = arraydata[position]

        //set data
        name.text =data.name
//        itemWeight.text = data.
        itemFakeCost.text =data.discount.toString()
        itemDiscountCost.text = data.discountPrice.toString()
        itemPic.setImageResource(data.img)

        return myView
    }

}