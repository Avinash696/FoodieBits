package com.example.zepto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.squareup.picasso.Picasso

class adapterDashboard(
    private val context: Context,
    private val arraydata: ArrayList<cardItemWithoutId>
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
            myView =  inflate.inflate(R.layout.row_adminlist,viewGroup,false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvItemDetailName)
        val discount = myView!!.findViewById<TextView>(R.id.tvItemDiscountedDetailCost)
        val btnAdd = myView!!.findViewById<ImageView>(R.id.ivAddBtnDetail)
        val itemPic = myView.findViewById<ImageView>(R.id.ivItemDetailPic)

        val data = arraydata[position]

        //set data
        name.text =data.name
        discount.text =data.discountPrice.toString()
        itemPic.setImageResource(data.img)
//        Picasso.get().load("http://56testing.club//imgFolder/uploads/admins/${data.img}").into(itemPic)
        //temp icon click screen forward move
        myView.setOnClickListener {
//            val intent = Intent(context, SingleTrendingActivity::class.java)
//            Log.d("ttt", "onBindViewHolder:${data.Price} ${data.discount}  ${data.discountPrice}")
//            context.startActivity(intent)
            Toast.makeText(context, "$name", Toast.LENGTH_SHORT).show()
        }

//        btnAdd.setOnClickListener {
//            //data send also
//            Log.d("nameArr", "onBindViewHolder:${data.img}")
//            Toast.makeText(context, "Item Added" + data.img, Toast.LENGTH_SHORT).show()
//
//        }
        return myView
    }

}