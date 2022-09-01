//package com.example.zepto.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.zepto.databinding.RowCategoriesAdminBinding
//import com.example.zepto.databinding.RowSubListAdminBinding
//import com.example.zepto.model.AdminCategoriesModel
//import com.example.zepto.ui.activity.AllItemSubAdminActivity
//import com.example.zepto.ui.activity.FaqsActivity
//import com.example.zepto.ui.activity.SubListSubAdminActivity
//
//class recyclerAdapterItem (private val arrayData: ArrayList<AdminCategoriesModel>,
//private val context: Context,
//) :
//RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    lateinit var binding:RowSubListAdminBinding
//
//    class CustomViewHolder(binding: RowSubListAdminBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        binding =
//            RowSubListAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CustomViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = arrayData[position]
//        //status +date
//        binding.tvAdminId.text = data.id.toString()
//        binding.tvAdminCatName.text = data.name
//        binding.ivAdminCategory.setImageResource(data.img)
//
//
//        //onclick on item
//        holder.itemView.setOnClickListener {
//            context.startActivity(Intent(context, FaqsActivity::class.java))
//        }
//        //item  with id will added
//        binding.btSubAdminCat.setOnClickListener {
//            context.startActivity(Intent(context, AllItemSubAdminActivity::class.java))
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return arrayData.size
//    }
//}