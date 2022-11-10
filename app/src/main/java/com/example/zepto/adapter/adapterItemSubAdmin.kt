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
import com.example.zepto.model.cardItemWithoutId
import com.squareup.picasso.Picasso

class adapterItemSubAdmin(
    private val context: Context,
    private val arraydata: ArrayList<cardItemModel>
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
            myView = inflate.inflate(R.layout.row_adapter_item_subadmin, viewGroup, false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvItemSubListName)
        val discount = myView!!.findViewById<TextView>(R.id.tvItemDiscountedSubListCost)
//        val btnAdd = myView!!.findViewById<ImageView>(R.id.ivItemSubListPic)
        val itemPic = myView.findViewById<ImageView>(R.id.ivItemSubListPic)

        val data = arraydata[position]

        //set data
        name.text = data.name
        discount.text = data.itemCount.toString()
        Picasso.get()
            .load(data.img)
            .into(itemPic)
//        itemPic.setImageResource(data.img)

//        val dialog = Dialog(context)
//        dialog.setContentView(R.layout.dialog_show_form)
//        //temp icon click screen forward move
//        myView.setOnClickListener {
//            dialog.show()
//        }


        return myView
    }
}
