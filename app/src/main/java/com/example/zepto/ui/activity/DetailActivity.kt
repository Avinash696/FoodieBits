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
import com.example.test.AviInterface
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterListDetail
import com.example.zepto.databinding.ActivityDetailBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.*
import com.example.zepto.module.Toasty
import com.example.zepto.ui.fragment.FiirstFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var currentUserLogin: String

    private lateinit var bottomNavHome: BottomNavigationView
    private lateinit var detailViewModel: DetailViewModel
    var categoryIdFlowKey: Int = 0
    var tempCatArrayIntent = ArrayList<cardItemModel>()

    //intent send data 3 var
    var intentName: ArrayList<String> = ArrayList<String>()
    var intentAmount: ArrayList<Int> = ArrayList<Int>()
    var intentImg: ArrayList<String> = ArrayList<String>()
    var detailItem = ArrayList<cardItemModel>()
    var trendingItem = ArrayList<SubCategoryImgX>()
    val commonCart =ArrayList<cartCommonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        changeFragment(SecondFragment(detailViewModel))
//        changeFragment(MapsFragment(detailViewModel))
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        // count update
//        detailViewModel.arrayCategoryLiveData.observe(this) {
//            tempCatArrayIntent.addAll(it)
//
//            for (item in it) {
////                detailItem.add(item)
//                intentName.add(item.name)
//                intentAmount.add(item.Price)
//                intentImg.add(item.img)
//                hitPostCartTrending(item.name, item.Price, item.img)
//                updateTotal(currentUserLogin)
//            }
//        }

        val receiveIntent = intent
//        val arrayTrendIntent = receiveIntent.getStringArrayExtra("trendingItemArrayKey")
        val arrayTrendIntent = receiveIntent.getParcelableArrayListExtra<SubCategoryImgX>("trendingItemArrayKey")

        if(arrayTrendIntent != null )
        detailViewModel.setTrending(arrayTrendIntent)

        detailViewModel.arrayCatData.observe(this){
            Log.d("foodieType", "cart: $it  ${it.size}")
            detailItem.addAll(it)
        }
        detailViewModel.arrayTrendingData.observe(this){
            Log.d("foodieType", "trending:   $it")
            trendingItem.addAll(it)
        }

        if(detailItem != null && trendingItem != null){
            for (item in detailItem)
//            commonCart.add(cartCommonModel(item.id,item.img,item.name,item.itemCount,item.Price))
                Log.d("mobiTestLog", "detailItem  $detailItem")

            for (i in trendingItem)
                Log.d("mobiTestLog", "trending: $trendingItem")
//                commonCart.add(cartCommonModel(i.id,i.productImg,i.productName,Integer.parseInt(i.productQty),Integer.parseInt(i.priceShow)))
        }

        //common data
        detailViewModel.combineBoth()
        detailViewModel.arrayLiveCommonData.observe(this){
                commonCart.addAll(it)
            binding.tvCartCountDetail.text = commonCart.size.toString()
        }

        val beautyIntent = receiveIntent.getIntExtra("beautyKey", 0)
        val counterIntent = receiveIntent.getIntExtra("counterKey", 0)
        currentUserLogin = receiveIntent.getStringExtra("currentUserLogin").toString()
        categoryIdFlowKey = Integer.parseInt(receiveIntent.getStringExtra("categoryIdFlowKey"))

//        val homeName = receiveIntent.getStringArrayListExtra("nameArrayKey")
//        val homeAmount = receiveIntent.getStringArrayListExtra("amountArrayKey")
//        val homeImg = receiveIntent.getIntegerArrayListExtra("imgArrayKey")
//
//        listViewCategory()
//        populatinDataFragment(beautyIntent)

        bottomNavHome = binding.bottomNavigation
        bottomNav()


        //count on click
        binding.llCartDetail.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
//            intent.putExtra("nameArray", intentName)
//            intent.putExtra("amountArray", intentAmount)
//            intent.putExtra("imgArray", intentImg)
//            intent.putExtra("tempCurrentUser", currentUserLogin)
//            intent.putExtra("ArrayDataCat", detailItem)
            Log.d("gfkjd", "detail: ${commonCart}")
            intent.putExtra("commonCartKey",commonCart)
            startActivity(intent)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flDetail, fragment)
        fragmentTransaction.commit()
    }

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
        binding.lvCategory.setOnItemClickListener { _, _, position, _ ->

            hitGetProduct(Integer.parseInt(body!!.subCategoryImg[position].subCategoryId))
        }
    }

    override fun onStart() {
        updateTotal(currentUserLogin)
        hitGetSubCategory()
        super.onStart()
    }

    private fun hitGetSubCategory() {
        val client = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = client.getMainSubCategoryFiltered(categoryIdFlowKey)
            if (call.isSuccessful)
                subCategoryItem(call.body())
            else
                Log.d("detailActivityTEst", "hitGetSubCategory: ${call.errorBody()}")
        }
    }

    private fun hitGetProduct(id: Int) {
        val client = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = client.getMainProductFiltered(id)
            if (call.isSuccessful)
//                subCategoryItem(call.body())
                changeFragment(FiirstFragment(detailViewModel, call.body()))

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

    private fun hitPostCartTrending(name: String, price: Int, img: String) {

        val currentUser = RequestBody.create(MediaType.parse("text/plain"), currentUserLogin)
        val itemNo = RequestBody.create(MediaType.parse("text/plain"), price.toString())
        val cartItem = RequestBody.create(MediaType.parse("text/plain"), name)
        val cartPrice = RequestBody.create(MediaType.parse("text/plain"), price.toString())
        val itemImgUrl = RequestBody.create(MediaType.parse("text/plain"), img)

        val retro = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = retro.postCartDetail(currentUser, cartItem, itemNo, cartPrice, itemImgUrl)
            if (call.isSuccessful) {
                Toasty.getToasty(applicationContext, call.body()!!.message)
            } else
                Toasty.getToasty(applicationContext, "${call.errorBody()!!}")
        }
    }
    private fun updateTotal(action :String){

        var reto = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = reto.getCartDetail(action)
            if(call.isSuccessful) {
                Log.d("actionNew", "action: $action ${call.body()}")
//                binding.tvCartCountDetail.text = call.body()!!.categoryImg.size.toString()
            }
            else
                Toasty.getToasty(applicationContext,"${call.errorBody()}")
        }
    }
}