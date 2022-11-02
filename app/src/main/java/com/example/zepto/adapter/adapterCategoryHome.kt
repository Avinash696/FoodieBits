package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.RowCategoriesAdminBinding
import com.example.zepto.model.CategoryImg
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.ui.activity.DetailActivity
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.SubListSubAdminActivity
import com.squareup.picasso.Picasso

class adapterCategoryHome(
    private val intentItemTrending:ArrayList<SubCategoryImgX>,
    private val context: Context,
    private val arraydata: ArrayList<CategoryImg>,
    private val tempCurrentUser:String
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
        var myView = convertView
        if (myView == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView = inflate.inflate(R.layout.row_category_cart, viewGroup, false)
        }
        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.ivClientFoodNameCategory)
        val itemPic = myView.findViewById<ImageView>(R.id.ivClientFoodImageCategory)


        val data = arraydata[position]
        Log.d("adapterCati", "getView: $data")
        //set data
        name.text = data.categoryName
        Picasso.get().load(data.categoryImg).into(itemPic)
//        if(data.categoryStatus == 0){
//            //set data
//            name.text =data.categoryName
//            Picasso.get().load(data.categoryImg).into(itemPic)
//        }
//        else {
//            myView.visibility = View.GONE
//        }
        Log.d("myCustomTrendingItem", "getView:$intentItemTrending ")
        //temp icon click screen forward move
        myView.setOnClickListener {
            Toast.makeText(context, data.id, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("categoryIdFlowKey", data.id)
            intent.putExtra("currentUserLogin", tempCurrentUser)
            intent.putExtra("trendingItemArrayKey",intentItemTrending)
            Log.d("mobiTestLog", "HomeAdapter: $intentItemTrending ${intentItemTrending?.size}")
            context.startActivity(intent)
        }
        return myView
    }

}