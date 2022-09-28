package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.test.aviInterface
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterListDetail
import com.example.zepto.databinding.ActivityDetailBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.listCategory
import com.example.zepto.model.mainSubCategoryModel
import com.example.zepto.ui.fragment.FiirstFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    private lateinit var listAdapter: ArrayAdapter<listCategory>
    private lateinit var listDetailAdapter: adapterListDetail
    private lateinit var bottomNavHome: BottomNavigationView
    private lateinit var detailViewModel: DetailViewModel
    var categoryIdFlowKey: Int = 0

    //intent send data 3 var
    var intentName: ArrayList<String> = ArrayList<String>()
    var intentAmount: ArrayList<Int> = ArrayList<Int>()
    var intentImg: ArrayList<Int> = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        changeFragment(SecondFragment(detailViewModel))
//        changeFragment(MapsFragment(detailViewModel))
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val receiveIntent = intent
        val beautyIntent = receiveIntent.getIntExtra("beautyKey", 0)
        val counterIntent = receiveIntent.getIntExtra("counterKey", 0)
        categoryIdFlowKey = Integer.parseInt(receiveIntent.getStringExtra("categoryIdFlowKey"))

//        val homeName = receiveIntent.getStringArrayListExtra("nameArrayKey")
//        val homeAmount = receiveIntent.getStringArrayListExtra("amountArrayKey")
//        val homeImg = receiveIntent.getIntegerArrayListExtra("imgArrayKey")
//
//        Log.d("pen", "onCreate: $homeAmount $homeName $homeImg ${homeName!!.size}")

        Log.d("detailCount", "onCreate: $categoryIdFlowKey")
        //counter set
        detailViewModel.countMutableLiveData.value = counterIntent

        Log.d("myrules", "onCreate: $beautyIntent")
//        listViewCategory()
//        populatinDataFragment(beautyIntent)

        bottomNavHome = binding.bottomNavigation
        bottomNav()

        //viewmodel 3 var observe

        detailViewModel.arrayNameDetail.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentName.add(it[i])
            }
        }

        detailViewModel.arrayImageDetail.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentImg.add(it[i])
            }
        }
        detailViewModel.arrayAmountDetail.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentAmount.add(it[i])
            }
        }
        detailViewModel.countMutableLiveData.observe(this) {
            binding.tvCartCountDetail.text = it.toString()
        }

        //count on click
        binding.llCartDetail.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("nameArray", intentName)
            intent.putExtra("amountArray", intentAmount)
            intent.putExtra("imgArray", intentImg)
            startActivity(intent)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        detailViewModel.updatingCount()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flDetail, fragment)
        fragmentTransaction.commit()
    }

    //    private fun listViewCategory() {
//        val arr = ArrayList<listCategory>()
//        arr.add(listCategory(R.drawable.f1, "All Item"))
//        arr.add(listCategory(R.drawable.f2, "Watermelon"))
//        arr.add(listCategory(R.drawable.f3, "Fruits"))
//        arr.add(listCategory(R.drawable.f4, "Spinach "))
//        arr.add(listCategory(R.drawable.f5, "Tomato"))
//
////        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
//        listDetailAdapter = adapterListDetail(this, arr)
//        binding.lvCategory.adapter = listDetailAdapter
//
//        binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
//            when (position) {
//                0 -> {
//                    changeFragment(FiirstFragment(detailViewModel))
//                }
//                1 -> {
//                    changeFragment(SecondFragment(detailViewModel))
//                }
//                2 -> {
//                    changeFragment(ThirdFragment(detailViewModel))
//                }
//                3 -> {
//                    changeFragment(FourthFragment(detailViewModel))
//                }
//            }
//            Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
//        }
//    }
    //bottom nav dialog
    private fun dialogCategories() {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        dialog.show()
    }


    private fun subCategoryItem(body: mainSubCategoryModel?) {
        GlobalScope.launch(Dispatchers.Main) {
            val adapter = adapterListDetail(applicationContext, body)
            binding.lvCategory.adapter = adapter
        }
        binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
            Log.d("lvAdapterCheck", "subCategoryItem: ${body!!.subCategoryImg[position].subCategoryId}")
            hitGetProduct(Integer.parseInt(body!!.subCategoryImg[position].subCategoryId))
        }
    }

    override fun onStart() {
        hitGetSubCategory()
        super.onStart()
    }

    private fun hitGetSubCategory() {
        val client = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = client.getMainSubCategoryFiltered(categoryIdFlowKey)
            if (call.isSuccessful)
                subCategoryItem(call.body())
            else
                Log.d("detailActivityTEst", "hitGetSubCategory: ${call.errorBody()}")
        }
    }

    private fun hitGetProduct(id: Int) {
        val client = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = client.getMainProductFiltered(id)
            if (call.isSuccessful)
//                subCategoryItem(call.body())
                changeFragment(FiirstFragment(detailViewModel,call.body()))
//                Log.d("detailActivityTEst", "hitGetSubCategory: ${call.body()!!.subProductImg[0]}")
            else
                Log.d("detailActivityTEst", "hitGetSubCategory: ${call.errorBody()}")
        }
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
                    startActivity(Intent(this, OrderListActivity::class.java))
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
}