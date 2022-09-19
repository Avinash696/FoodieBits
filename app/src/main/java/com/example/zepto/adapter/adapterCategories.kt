package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.viewModel.ItemCountViewModel
import com.squareup.picasso.Picasso


class adapterCategories(
    private val context: Context,
    private val arraydata: ArrayList<cardItemModel>
) :
    BaseAdapter() {
    private var cartName: ArrayList<String> = ArrayList()
    private var cartAmount: ArrayList<Int> = ArrayList()
    private var cartImage: ArrayList<Int> = ArrayList()
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

        var myView = convertView
        if (myView == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView = inflate.inflate(R.layout.catagories_row, viewGroup, false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvCateItemName)
        val itemPic = myView.findViewById<ImageView>(R.id.ivCateItemPic)

        val data = arraydata[position]
        Log.d("instantDelete", "getView: ")
        Log.d("instantDelete", "getView: ${data.img}")
        //set data
        name.text = data.name
        Picasso.get().load(data.img).into(itemPic)
//        itemPic.setImageResource(data.img)


        itemPic.setOnClickListener {
//            context.startActivity(Intent(context, DetailActivity::class.java))
            val intent = Intent(context, SingleTrendingActivity::class.java)
//            Log.d("catAdapter", "AdapterCate:${data.Price} ${data.discount}  ${data.discountPrice}")
            intent.putExtra("amountKey",data.Price)
            intent.putExtra("nameKey",data.name)
            intent.putExtra("imgKey",data.img)
//            intent.putExtra("counterKey",)
            cartName.add(data.name)
            cartAmount.add(data.Price)
//            cartImage.add(data.img)



            context.startActivity(intent)
        }
        return myView
    }

}