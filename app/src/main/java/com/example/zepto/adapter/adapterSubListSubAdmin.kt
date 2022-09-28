package com.example.zepto.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.ui.activity.AllItemSubAdminActivity
import com.squareup.picasso.Picasso

class adapterSubListSubAdmin(
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
        var myView = convertView
        if (myView == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView = inflate.inflate(R.layout.row_sub_list_admin, viewGroup, false)
        }

        //fields assign
        val name = myView!!.findViewById<TextView>(R.id.tvSubAdminName)

        val itemPic = myView.findViewById<ImageView>(R.id.ivSubAdmin)

        val data = arraydata[position]
        Log.d("pager", "getView: $data")
        name.text = data.name
        Picasso.get()
            .load(data.img)
            .into(itemPic)

        val dialog =Dialog(context)
        dialog.setContentView(R.layout.dialog_select_operation)
        val operation = dialog.findViewById<Button>(R.id.btActivateDeactivate)
        val addProduvt = dialog.findViewById<Button>(R.id.btAddProductSelect)

        //temp icon click screen forward move
        myView.setOnClickListener {
            Log.d("flowListWala", "getView:${data} ")
            val intent = Intent(context,AllItemSubAdminActivity::class.java)
            intent.putExtra("SubProducNameKey",data.name)
            intent.putExtra("SubProductIdKey",data.id)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
        return myView
    }

}