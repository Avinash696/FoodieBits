package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.zepto.R
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.databinding.ActivityProfileBinding
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.viewModel.ItemCountViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val addressArr = ArrayList<String>()
    private lateinit var bottomNavHome: BottomNavigationView
    private lateinit var countViewModel: ItemCountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        countViewModel = ViewModelProvider(this)[ItemCountViewModel::class.java]
        init()
        binding.ivAddAddress.setOnClickListener {
            addAddress()
        }
        binding.lvAddAdress.setOnItemLongClickListener { adapterView, view, i, l ->
          val toRemove = addressArr[i-1]
            addressArr.remove(toRemove)
//            addressArr.removeAt(i)
            Log.d("capacitor", "onCreate: ${ addressArr[i-1]}")
            true
        }
        bottomNav()
    }
    fun init(){
        bottomNavHome = binding.bottomNavigation
    }
    private fun addAddress() {
        addressArr.add(binding.etaddAddress.text.toString())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, addressArr)
        binding.lvAddAdress.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun bottomNav() {
        bottomNavHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.menu_category -> {
                    dialogCategories()
//                    startActivity(Intent(this, AdminActivity::class.java))
                    true
                }
                R.id.menu_order -> {
                    startActivity(Intent(this, OrderSummaryActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.menu_help -> {
                    startActivity(Intent(this, FaqsActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    private fun dialogCategories() {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

//        val arrayList = ArrayList<cardItemWithoutId>()
//        arrayList.add(cardItemWithoutId(12, R.drawable.by1, "Beauty", 2, 3))
//        arrayList.add(cardItemWithoutId(11, R.drawable.instant1, "Instant Food", 2, 3))
//        arrayList.add(cardItemWithoutId(21, R.drawable.cd1, "Cold Drink", 2, 3))
//        arrayList.add(cardItemWithoutId(31, R.drawable.biscut, "Biscuts", 2, 3))
//        arrayList.add(cardItemWithoutId(41, R.drawable.c1, "Choco", 2, 3))
//        arrayList.add(cardItemWithoutId(51, R.drawable.m1, "Masala", 2, 3))
//        arrayList.add(cardItemWithoutId(61, R.drawable.oil1, "Oil", 2, 3))
//        arrayList.add(cardItemWithoutId(71, R.drawable.s1, "Sauce", 2, 3))
//        arrayList.add(cardItemWithoutId(17, R.drawable.coffee0, "Coffee", 2, 3))
//        arrayList.add(cardItemWithoutId(15, R.drawable.gt1, "Green Tea", 2, 3))
//        arrayList.add(cardItemWithoutId(14, R.drawable.tea1, "Tea ", 2, 3))
//        arrayList.add(cardItemWithoutId(13, R.drawable.clean_item, "Home Clean", 2, 3))
//
//        val adapter = adapterCategories(this, arrayList)
//        simpleCategories.adapter = adapter

        dialog.show()
    }
}