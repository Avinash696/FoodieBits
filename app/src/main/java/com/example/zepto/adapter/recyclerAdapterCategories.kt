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
import com.example.test.AviInterface
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
//    var counter: Int = 0

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
            binding.btCardMainCategoryActive.text = "Activate"
        } else if(data.categoryStatus == 0) {
            binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
            binding.btCardMainCategoryActive.text = "Deactivate"
        }

        //status +date
        Log.d("flow", "onBindViewHolder: $data")
//        binding.tvAdminId.text = data.id
        binding.tvAdminCatName.text = data.categoryName
//        binding.ivAdminCategory.setImageResource(data.img)
        Picasso.get()
            .load(data.categoryImg)
            .into(binding.ivAdminCategory)

//        onclick on item
        binding.btCardMainCategoryActive.setOnClickListener {
             if (data.categoryStatus == 1) {
//                counter = 0
                updateStatus(data,0)
//                 binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.red))
//                 binding.btCardMainCategoryActive.text = "Deactivate"
            } else {
//                 counter=  1
                 updateStatus(data,1)
//                 binding.btCardMainCategoryActive.setBackgroundColor(context.getColor(R.color.green))
//                 binding.btCardMainCategoryActive.text = "Activate"
            }
            notifyItemChanged(position)
        }

        //item  with id will added
        binding.btSubAdminSubCat.setOnClickListener {
//            Log.d("subClicked", "onBindViewHolder: u clicked that sublist ")
            val intent = Intent(context, SubListSubAdminActivity::class.java)
            intent.putExtra("SubCatKey", data.categoryName)
            intent.putExtra("SubCatIdKey", data.id)
            Log.d("flow", "onBindViewHolder: ${data.categoryName} ${data.id}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun updateStatus(data :CategoryImg, counter:Int){
        Log.d("fewSecond", "updateStatus: ${data.id}  $counter")
        val client = RetrofitHelper.getClient().create(AviInterface::class.java)
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