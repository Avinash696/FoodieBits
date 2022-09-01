package com.example.zepto.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.databinding.RowCheckoutAdminBinding
import com.example.zepto.databinding.RowUserMasterBinding
import com.example.zepto.model.adminOrderMaster
import com.example.zepto.model.userMasterModel
import com.example.zepto.ui.activity.FaqsActivity

class adapterUserMasterAdmin(
    private val arrayData: ArrayList<userMasterModel>,
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RowUserMasterBinding

    class CustomViewHolder(binding: RowUserMasterBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            RowUserMasterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayData[position]
        binding.tvIdUM.text = data.id.toString()
        binding.tvNameUM.text = data.name
        binding.tvEmailUM.text = data.email
        binding.tvMobileNoUM.text = data.mobile.toString()
        binding.tvDateUM.text = data.date

        //btn click
        binding.btEditUser.setOnClickListener {
            dialogEditUser()
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    private fun dialogEditUser() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_user_master)
        dialog.show()
    }
}
