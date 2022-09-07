package com.example.zepto.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zepto.R
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
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
//        var  view = View.inflate(context,R.layout.activity_list_item,null)
//
//        var name :String= view.findViewById(R.id.na)
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

//        //set data
//        name.text =data.name
//        discount.text =data.discount.toString()
//        itemPic.setImageResource(data.img)

        //
        name.text = data.name
        Picasso.get()
            .load(data.img)
            .into(itemPic)
//        itemPic.setImageResource(data.img)
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_select_operation)
        val operation = dialog.findViewById<Button>(R.id.btActivateDeactivate)
        val addProduvt = dialog.findViewById<Button>(R.id.btAddProductSelect)


        //temp icon click screen forward move
//        myView.setOnClickListener {
//            dialog.show()
//            addProduvt.setOnClickListener {
//                val intent = Intent(context, AllItemSubAdminActivity::class.java)
//                context.startActivity(intent)
//            }
//            operation.setOnClickListener {
//                operation.text = "Deactivate"
//            }
//        }
        return myView
    }

}