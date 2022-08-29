package com.example.zepto.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityAddProductFormBinding

class AddProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = "Add Particular Item"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product_form)

        binding.btAddMoreAlikeItem.setOnClickListener {
//            binding.llItemAlike.visibility = View.VISIBLE
//            when(it.id){
//                R.id.llAddItem.equals(View.GONE)
//            }
//            it.id =
//            if (it.equals(View.INVISIBLE)) {
//                binding.llItemAlike.visibility = View.VISIBLE
//            }
//            else {
//                binding.llItemAlike.visibility = View.INVISIBLE
//            }
            when (binding.llItemAlike.visibility) {
                View.GONE -> {
                    binding.llItemAlike.visibility = View.VISIBLE
                    return@setOnClickListener
                }
                View.VISIBLE -> {
                    binding.llItemAlike.visibility = View.GONE
                    return@setOnClickListener}
            }
        }

        binding.btSubmitProduct.setOnClickListener {
            Toast.makeText(this, "Sumbited", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AllItemSubAdminActivity::class.java))
        }
    }

}