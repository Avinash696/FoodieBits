package com.example.zepto.ui.activity

import  android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.adapter.adapterItemSubAdmin
import com.example.zepto.constant.constants
import com.example.zepto.databinding.ActivityAllItemSubAdminBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.model.mainSubProductResponceModel
import com.example.zepto.module.Toasty
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class AllItemSubAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemSubAdminBinding
    val arrayList = ArrayList<cardItemWithoutId>()
    lateinit var data: cardItemWithoutId
    lateinit var  id_data:String

    //    lateinit var dialog :Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_all_item_sub_admin
        )

        val intent = intent
         id_data = intent.getStringExtra("SubProductIdKey").toString()
        val name_data = intent.getStringExtra("SubProductNameKey")
        constants.subPRoductId = id_data

        supportActionBar!!.title = name_data
        Log.d("flowListWala", "onCreate: $id_data $name_data")


        binding.ivAddAdminUSer.setOnClickListener {
            //add
            val intent = Intent(Intent(this, AddProductFormActivity::class.java))
            intent.putExtra("SubFormIdKey",id_data)
            intent.putExtra("SubFormNameKey",name_data)
            Log.d("flowListWala", "ivAdd: $id_data $name_data")
            startActivity(intent)
        }
    }

    override fun onStart() {
        hitMainCategoryApi()
        super.onStart()
    }

    private fun populatingData(data: mainSubProductResponceModel) {
        val arrayList = ArrayList<cardItemModel>()
        for (i in 0 until data.subProductImg.size) {
            val dumy = data.subProductImg[i]
            arrayList.add(
                cardItemModel(
                    title.toString(),
                    dumy.addProductImg,
                    dumy.addProductName,
                    dumy.addProductQuantity,
                    Integer.parseInt(dumy.addProductPrice)
                )
            )
        }
        GlobalScope.launch(Dispatchers.Main) {
            val adapterItemSubAdmin = adapterItemSubAdmin(applicationContext, arrayList)
            val listView = binding.lvAddUserItem
            listView.adapter = adapterItemSubAdmin
        }

    }

    private fun hitMainCategoryApi() {
         val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch {
//            val call = repo.getMainSubProduct()
            Log.d("var check", "hitMainCategoryApi:${constants.subPRoductId} ")
            val call = repo.getMainProductFiltered(Integer.parseInt(constants.subPRoductId))
            Log.d("myFun", "hitMainCategoryApi: inside $call")
            if (call.isSuccessful) {
                val gson = Gson()
                populatingData(call.body()!!)
                Toasty.getToasty(applicationContext,"Success Fetch")
            } else
                Toasty.getToasty(applicationContext,"${call.body()!!.error}")
        }
    }
}