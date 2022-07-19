package com.example.zepto.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityFaqsBinding
import com.example.zepto.databinding.FaqsActivity2Binding


class FaqsActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityFaqsBinding
    private lateinit var binding :FaqsActivity2Binding
    private val paths = arrayOf("item 1", "https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list", "item 3")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.faqs_activity2)

//        //spinner set
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_spinner_item, paths
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spHowToRegister!!.adapter = adapter
        showHidePoint()
    }
    private fun showHidePoint(){
        binding.tvPoint1Head.setOnClickListener {
            if (binding.tvPoint1Desc.visibility == View.VISIBLE)
                binding.tvPoint1Desc.visibility = View.GONE;
            else
                binding.tvPoint1Desc.visibility = View.VISIBLE;
        }
        binding.tvPoint2Head.setOnClickListener {
            if (binding.tvPoint2Desc.visibility == View.VISIBLE)
                binding.tvPoint2Desc.visibility = View.GONE;
            else
                binding.tvPoint2Desc.visibility = View.VISIBLE;
        }
        binding.tvPoint3Head.setOnClickListener {
            if (binding.tvPoint3Desc.visibility == View.VISIBLE)
                binding.tvPoint3Desc.visibility = View.GONE;
            else
                binding.tvPoint3Desc.visibility = View.VISIBLE;
        }
        binding.tvPoint4Head.setOnClickListener {
            if (binding.tvPoint4Desc.visibility == View.VISIBLE)
                binding.tvPoint4Desc.visibility = View.GONE;
            else
                binding.tvPoint4Desc.visibility = View.VISIBLE;
        }
        binding.tvPoint5Head.setOnClickListener {
            if (binding.tvPoint5Desc.visibility == View.VISIBLE)
                binding.tvPoint5Desc.visibility = View.GONE;
            else
                binding.tvPoint5Desc.visibility = View.VISIBLE;
        }
    }
}