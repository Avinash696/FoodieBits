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

        //temp icon click screen forward move
        myView.setOnClickListener {
            val intent = Intent(context, SingleTrendingActivity::class.java)
            Log.d("ttt", "onBindViewHolder:${data.Price} ${data.discount}  ${data.discountPrice}")
            intent.putExtra("amountKey", data.Price)
            intent.putExtra("nameKey", data.name)
            intent.putExtra("imgKey", data.img)
            //testing onclick itemView
            intent.putExtra("nameArray", cartName)
            intent.putExtra("amountArray", cartAmount)
            intent.putExtra("imageArray", cartImage)
            Log.d("adapterCountCheck", "getView:${countViewMode.count} ")
            intent.putExtra("singleCountTrend", countViewMode.count)

            //count send
            context.startActivity(intent)
        }

        btnAdd.setOnClickListener {
            //data send also

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
//            countViewMode.count = cartName.size
//            countViewMode.setCount()
            //mutable count update
            countViewMode.count = countViewMode.countMutableLiveData.value!! +1
            countViewMode.setCount()
            Log.d("justdd", "onBindViewHolder:${countViewMode.count} ")
        }
        return myView
    }

}