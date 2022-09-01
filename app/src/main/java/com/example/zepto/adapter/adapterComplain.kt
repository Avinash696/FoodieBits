package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.zepto.R
import com.example.zepto.model.adminComplainModel

class adapterComplain(
private val context: Context,
private val arraydata: ArrayList<adminComplainModel>
) :
BaseAdapter() {
    var cartName: ArrayList<String> = ArrayList()
    var cartAmount: ArrayList<Int> = ArrayList()
    var cartImage: ArrayList<Int> = ArrayList()
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
            myView =  inflate.inflate(R.layout.row_complain,viewGroup,false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvNameComplain)
        val complainId = myView!!.findViewById<TextView>(R.id.tvComplainIdComplain)
        val id = myView!!.findViewById<TextView>(R.id.tvIDComplain)
        val date = myView!!.findViewById<TextView>(R.id.tvDateComplain)
        val mobileNo = myView!!.findViewById<TextView>(R.id.tvMObileNoComplain)
//        val btnAdd = myView!!.findViewById<ImageView>(R.id.ivAddBtnDetail)
//        val itemPic = myView.findViewById<ImageView>(R.id.ivItemDetailPic)

        val data = arraydata[position]

        //set data
        name.text =data.name
        complainId.text =data.ComplainID.toString()
        id.text = data.id.toString()
        date.text =data.date
        mobileNo.text = data.mobileNo.toString()


        //temp icon click screen forward move
        myView.setOnClickListener {
            Toast.makeText(context, "$name", Toast.LENGTH_SHORT).show()
        }

        return myView
    }

}