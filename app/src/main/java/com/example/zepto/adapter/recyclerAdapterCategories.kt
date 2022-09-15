package com.example.zepto.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.databinding.OrderHistoryBinding
import com.example.zepto.databinding.RowCategoriesAdminBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.AdminCategoriesModel
import com.example.zepto.model.CategoryImg
import com.example.zepto.model.orderListModel
import com.example.zepto.ui.activity.FaqsActivity
import com.example.zepto.ui.activity.SingleTrendingActivity
import com.example.zepto.ui.activity.SubListSubAdminActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

class recyclerAdapterCategories(
    private val arrayData: ArrayList<CategoryImg>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowCategoriesAdminBinding
    var counter: Int = 0

    class CustomViewHolder(binding: RowCategoriesAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowCategoriesAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]

        if (data.categoryStatus == 1) {
            binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))

        } else {
            binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))

        }

        //status +date
        Log.d("flow", "onBindViewHolder: $data")
        binding.tvAdminId.text = data.id
        binding.tvAdminCatName.text = data.categoryName
//        binding.ivAdminCategory.setImageResource(data.img)
        Picasso.get()
            .load(data.categoryImg)
            .into(binding.ivAdminCategory)

//        onclick on item
        binding.btCardMainCategoryActive.setOnClickListener {
             if (data.categoryStatus == 1) {
                counter = 0
                updateStatus(data,counter)
                 binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
                 binding.btCardMainCategoryActive.text = "Deactivate"
            } else {
                 counter=  1
                 updateStatus(data,counter)
                 binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
                 binding.btCardMainCategoryActive.text = "Activate"
            }
        }

        holder.itemView.setOnClickListener {

//            val status = RequestBody.create(MediaType.parse("text/plain"), counter.toString())
//            val id = RequestBody.create(MediaType.parse("text/plain"), data.id)
//            GlobalScope.launch {
//                val call = client.updateMainStatusCategory(id, status)
//                if (call.isSuccessful) {
//                    Log.d("adapterStatus", "onBindViewHolder: Success")
//                    binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
//                } else {
//                    Log.d("adapterStatus", "onBindViewHolder: failed")
//                    binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
//
//                }
//
////                val status = RequestBody.create(MediaType.parse("text/plain"), "0")
////                val id = RequestBody.create(MediaType.parse("text/plain"), data.id)
////                GlobalScope.launch {
////                    val call = client.updateMainStatusCategory(id, status)
////                    if(call.isSuccessful)
////                    {
////                        Log.d("adapterStatus", "onBindViewHolder: Success")
////                        binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
////                    }
////                    else {
////                        Log.d("adapterStatus", "onBindViewHolder: failed")
////                        binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
////
////                    }
////                }
////            }
////            else {
////                val status = RequestBody.create(MediaType.parse("text/plain"), "1")
////                val id = RequestBody.create(MediaType.parse("text/plain"), data.id)
////                GlobalScope.launch {
////                    val call = client.updateMainStatusCategory(id, status)
////                    if(call.isSuccessful)
////                        Log.d("adapterStatus", "onBindViewHolder: Success")
////                    else
////                        Log.d("adapterStatus", "onBindViewHolder: failed")
////                }
////            }
//            }
            //item  with id will added
            binding.btSubAdminSubCat.setOnClickListener {
                val intent = Intent(context, SubListSubAdminActivity::class.java)
                intent.putExtra("SubCatKey", data.categoryName)
                intent.putExtra("SubCatIdKey", data.id)
                Log.d("flow", "onBindViewHolder: ${data.categoryName} ${data.id}")
                context.startActivity(intent)
            }
        }
//        binding.btCardMainCategoryActive.setOnClickListener {
//
//            val client = RetrofitHelper.getClient().create(aviInterface::class.java)
//            if (data.categoryStatus == 1) {
//                val status = RequestBody.create(MediaType.parse("text/plain"), "0")
//                val call = client.updateMainStatusCategory(data.id, status)
//            } else {
//                val status = RequestBody.create(MediaType.parse("text/plain"), "0")
//            }
//
//
//        }
        //active set
//        binding.btCardMainCategoryActive.setOnClickListener {
//
//            if (data.categoryStatus == 0) {
//                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
//                binding.btCardMainCategoryActive.text = "Active"
//                //update status id
//
//            } else {
//                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
//                binding.btCardMainCategoryActive.text = "Deactivate"
//            }
//        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun updateStatus(data :CategoryImg, counter:Int){
        Log.d("fewSecond", "updateStatus: ${data.id}  $counter")
        val client = RetrofitHelper.getClient().create(aviInterface::class.java)
        val status = RequestBody.create(MediaType.parse("text/plain"), counter.toString())
        val id = RequestBody.create(MediaType.parse("text/plain"), data.id)
        GlobalScope.launch(Dispatchers.IO) {
            val call = client.updateMainStatusCategory(id, status)
            if (call.isSuccessful) {
                Log.d("adapterStatus", "onBindViewHolder: Success $id $status")
                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
            } else {
                Log.d("adapterStatus", "onBindViewHolder: failed $id $status")
                binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
            }
        }
    }
}