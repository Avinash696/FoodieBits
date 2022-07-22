package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.ui.activity.SingleTrendingActivity

class adapterSubListCategories(
    private val context: Context,
    private val arraydata: ArrayList<cardItemModel>,
    private var countViewMode: DetailViewModel
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
            myView =  inflate.inflate(R.layout.row_sublist,viewGroup,false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvItemDetailName)
        val discount = myView!!.findViewById<TextView>(R.id.tvItemDiscountedDetailCost)
        val btnAdd = myView!!.findViewById<ImageView>(R.id.ivAddBtnDetail)
        val itemPic = myView.findViewById<ImageView>(R.id.ivItemDetailPic)

        val data = arraydata[position]

        //set data
        name.text =data.name
        discount.text =data.discount.toString()
        itemPic.setImageResource(data.img)

        btnAdd.setOnClickListener {
            context.startActivity(Intent(context, SingleTrendingActivity::class.java))
            //data send also
            val intent = Intent(context,SingleTrendingActivity::class.java)
//            intent.putExtra("nameKey",data.name)
//            intent.putExtra("amountKey",data.Price)
//            intent.putExtra("imgKey",data.img)
//            Log.d("anaja", "getView: ${data.name} ${data.Price} ${data.img}")
            Log.d("nameArr", "onBindViewHolder:${data.img}")
            Toast.makeText(context, "Item Added" + data.img, Toast.LENGTH_SHORT).show()

            cartName.add(data.name)
            cartAmount.add(data.Price)
            cartImage.add(data.img)

            //viewmodel data set
            countViewMode.arrayNameDetail.value = cartName
            countViewMode.arrayAmountDetail.value = cartAmount
            countViewMode.arrayImageDetail.value = cartImage
            //count update
            countViewMode.count = cartName.size
            countViewMode.setCount()
            Log.d("justdd", "onBindViewHolder:${countViewMode.count} ")
        }
        return myView
    }

}