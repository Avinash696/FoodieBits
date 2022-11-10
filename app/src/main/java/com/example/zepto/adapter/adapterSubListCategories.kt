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
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.module.Toasty
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.squareup.picasso.Picasso

class adapterSubListCategories(
    private val context: Context,
    private val arraydata: ArrayList<cardItemModel>,
    private val detailViewModel: DetailViewModel
) :
    BaseAdapter() {
    var cartName: ArrayList<String> = ArrayList()
    var cartAmount: ArrayList<Int> = ArrayList()
    var cartImage: ArrayList<Int> = ArrayList()

        var cartItem :ArrayList<cardItemModel> = ArrayList()
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
            myView = inflate.inflate(R.layout.row_sublist, viewGroup, false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvItemDetailName)
        val discount = myView.findViewById<TextView>(R.id.tvItemDiscountedDetailCost)
        val btnAdd = myView.findViewById<ImageView>(R.id.ivAddBtnDetail)
        val itemPic = myView.findViewById<ImageView>(R.id.ivItemDetailPic)

        val data = arraydata[position]

        //set data
        name.text = data.name
        discount.text = data.itemCount.toString()
        Log.d("tiger", "getView: $data")
        Picasso.get().load(data.img).into(itemPic)

        //temp icon click screen forward move
        myView.setOnClickListener {
//            val intent = Intent(context, SingleTrendingActivity::class.java)
//            intent.putExtra("amountKey", data.Price)
//            intent.putExtra("nameKey", data.name)
//            intent.putExtra("imgKey", data.img)
//            //testing onclick itemView
//            intent.putExtra("nameArray", cartName)
//            intent.putExtra("amountArray", cartAmount)
//            intent.putExtra("imageArray", cartImage)
//
//            //count send
//            context.startActivity(intent)

        }

        btnAdd.setOnClickListener {
            //data send also
                Toasty.getToasty(context,"added")
//            detailViewModel.updateCat(data)
//            Log.d("cartCheck", "getView: ${detailViewModel.arrayCategoryLiveData.value}")
//            Log.d("nameArr", "onBindViewHolder:${data.img}")
//            Toast.makeText(context, "Item Added" + data.img, Toast.LENGTH_SHORT).show()
//            cartName.add(data.name)
//            cartAmount.add(data.Price)
//            cartImage.add(data.img)
            detailViewModel.setCatItem(data)
        }
        return myView
    }

}