package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.R
import com.example.zepto.adapter.adapterSubListCategories
import com.example.zepto.adapter.adapterSubListSubAdmin

import com.example.zepto.databinding.ActivitySubListSubAdminBinding
import com.example.zepto.model.AdminCategoriesModel
import com.example.zepto.model.cardItemModel

class SubListSubAdminActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubListSubAdminBinding
    private lateinit var simpleCategories: GridView
    val arrayList = ArrayList<cardItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_list_sub_admin)
        simpleCategories = binding.gvSubListAdmin

        //intent
        val intent = intent
        val subCatKey = intent.getStringExtra("SubCatKey")
        supportActionBar?.title = subCatKey
        populatingData()
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_select_operation)
        simpleCategories.setOnItemClickListener { adapterView, view, i, l ->
            dialog.show()
            dialog.findViewById<ImageView>(R.id.dialogImgSelect).setImageResource(arrayList[i].img)
            dialog.findViewById<TextView>(R.id.dialogNameSelect).text = (arrayList[i].name)
            dialog.findViewById<Button>(R.id.btAddProductSelect).setOnClickListener {
                val intent = Intent(this,AllItemSubAdminActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun populatingData() {

        arrayList.add(cardItemModel(1, R.drawable.c1, "All Biscuit", 2, 3))
        arrayList.add(cardItemModel(2, R.drawable.c2, "CE11", 2, 3))
        arrayList.add(cardItemModel(3, R.drawable.c3, "CE71", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.c4, "CE61", 2, 3))
        arrayList.add(cardItemModel(5, R.drawable.c5, "CE15", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c6, "CE14", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c7, "CE13", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c8, "CE12", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c9, "CE11", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c10, "CE10", 2, 3))

        val adapter = adapterSubListSubAdmin(this, arrayList)
        simpleCategories.adapter = adapter
    }
}