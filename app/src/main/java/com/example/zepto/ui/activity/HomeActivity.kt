package com.example.zepto.ui.activity

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.AviInterface
import com.example.zepto.LoginActivity
import com.example.zepto.R
import com.example.zepto.adapter.adapterBanner
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.adapter.adapterCategoryHome
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.constant.PermissionUtils
import com.example.zepto.databinding.ActivityHomeBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.*
import com.example.zepto.module.Toasty
import com.example.zepto.viewModel.ItemCountViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvTrending: RecyclerView
    private lateinit var bottomNavHome: BottomNavigationView
    private lateinit var mainCateDialogData: mainCategoryModel
    private lateinit var trendingCateDialogData: trendingResponceModel
    private val LOCATION_PERMISSION_REQUEST_CODE = 888

    //appbar
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    lateinit var appBar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //    private var currentUser: String = "avi"
    private lateinit var currentUserLogin: String

    val repo = RetrofitHelper.getClient().create(AviInterface::class.java)

    //cart count
    val count = 0

    //intent send data 3 var
    var intentName: ArrayList<String> = ArrayList()
    var intentAmount: ArrayList<Int> = ArrayList()
    var intentImg: ArrayList<String> = ArrayList()
    //send parsable trending item
    var intentItemTrending = ArrayList<SubCategoryImgX>()

    //for trending
    val tempName = ArrayList<String>()
    val tempAmount = ArrayList<Int>()
    val tempImg = ArrayList<String>()

    //viewmodel cout
    private lateinit var countViewModel: ItemCountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.shimmerViewContainer?.startShimmer()
        init()
        val homeName = intent
        currentUserLogin = homeName.getStringExtra("homeName").toString()
        Log.d("tempCurrentUser", "onCreate: $currentUserLogin")

        showImg()
        hitMainCategoryApi()

        brandFocusBottom()
        brandFocusTop()

        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        //viewModel
//        countViewModel = ViewModelProvider(this, ItemCountViewModelFactory(cartResult()))[ItemCountViewModel::class.java]
        countViewModel = ViewModelProvider(this)[ItemCountViewModel::class.java]

        getTrendingItem()

//        countViewModel.itemTrendingLiveData.observe(this) {
//            for (item in it) {
//                tempName.add(item.productName)
//                tempAmount.add(Integer.parseInt(item.priceShow))
//                tempImg.add(item.productImg)
//                hitPostCartTrending(
//                    item.productName,
//                    Integer.parseInt(item.priceShow),
//                    item.productImg
//                )
//            }
//            binding.tvCartCount.text = tempName.size.toString()
//        }

        countViewModel.itemLiveCart.observe(this){
            Log.d("checkYrCart", "onCreate: ${it}")
////            for (item in it) {
////                tempName.add(item.productName)
////                tempAmount.add(Integer.parseInt(item.priceShow))
////                tempImg.add(item.productImg)
////
//////                hitPostCartTrending(
//////                    item.productName,
//////                    Integer.parseInt(item.priceShow),
//////                    item.productImg
//////                )
////
////            }
//            Log.d("checkYrCart", "size check: ${it.size}")
            intentItemTrending.clear()
            intentItemTrending.addAll(it)
            binding.tvCartCount.text = it.size.toString()
        }
        //cart
        binding.llCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
//            intent.putExtra("nameArray", tempName)
//            intent.putExtra("amountArray", tempAmount)
//            intent.putExtra("imgArray", tempImg)
//            intent.putExtra("tempCurrentUser", currentUserLogin)
            Log.d("rainWeight", "home: $intentItemTrending")
            intent.putExtra("cartTrendingKey",intentItemTrending)
            startActivity(intent)
        }

        //for tea banner
        binding.ivHomeTea.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("beautyKey", 10)
            startActivity(intent)
        }
        //for now search without fn
        binding.llSearchProduct.setOnClickListener {
            Toast.makeText(this, "Searching Itretem Plz Wait", Toast.LENGTH_SHORT).show()
        }

        //action
        setSupportActionBar(appBar)
        actionBarDrawableToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        actionBarDrawableToggle.syncState()

        //bottom nav set
        setSideNavBar()
        bottomNav()

        //onclick see item
        binding.tvTrendingSeeAll.setOnClickListener {
//            Log.d("dialogItem", "onCreate: ${trendingCateDialogData.subCategoryImg}")
//            Log.d("dialogItem", "Clicked Trend Cat")
            dialogHomeScreen(trendingCateDialogData)
        }
        //categories
        binding.tvCategoriesSeeAll.setOnClickListener {
//            Log.d("dialogItem", "onCreate: ${mainCateDialogData.categoryImg}")
//            Log.d("dialogItem", "clicked MAinCat")
            dialogCategories(mainCateDialogData)
        }
    }

    private fun setSideNavBar() {
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_allCategories -> {
                    dialogCategories(mainCateDialogData)
                    true
                }
                R.id.menu_order -> {
                    startActivity(Intent(this, OrderListActivity::class.java))
                    true
                }
                R.id.menu_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
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
                R.id.menu_myOrders -> {
                    startActivity(Intent(this, FaqsActivity::class.java))
                    true
                }
                R.id.menu_homeLogout -> {
                    val intnet = Intent(this, LoginActivity::class.java)
                    intnet.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intnet)
                    finish()
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun init() {
        navView = binding.nvHome
        appBar = binding.appBar
        drawerLayout = binding.mainDrawable
        rvTrending = binding.rvTrending
        bottomNavHome = binding.bottomNavigation
    }

    private fun bottomNav() {
        bottomNavHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.menu_category -> {
                    dialogCategories(mainCateDialogData)
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

    private fun showImg() {
        val handler = Handler()
        val r = Runnable {
        }
        handler.postDelayed(r, 5000)
    }

    private fun tendingItem(data: trendingResponceModel?) {
        val arrayList = ArrayList<CategoryImg>()
        for (i in 0 until data!!.subCategoryImg.size) {
            val dumy = data.subCategoryImg[i]
            Log.d("instantDelete", "tendingItem: ${Gson().toJson(data)}")
            arrayList.add(
//                cardItemModel(
//                    dumy.id, dumy.productImg, dumy.productName,
//                    Integer.parseInt(dumy.discountedPrice), Integer.parseInt(dumy.priceShow)
//                )
                CategoryImg(
                    dumy.productImg,
                    dumy.productName,
                    1,
                    dumy.id,
                    dumy.discountedPrice,
                    dumy.priceShow
                )
            )
        }
        GlobalScope.launch(Dispatchers.Main) {
            rvTrending.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            val arrayAdapter = adapterTrending(data.subCategoryImg, applicationContext, countViewModel)
            rvTrending.adapter = arrayAdapter
            binding.shimmerViewContainer?.hideShimmer()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_order -> startActivity(Intent(this, AdminActivity::class.java))
            R.id.menu_category -> startActivity(Intent(this, AdminActivity::class.java))
            R.id.menu_home -> startActivity(Intent(this, AdminActivity::class.java))
        }
        if (actionBarDrawableToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun dialogHomeScreen(data: trendingResponceModel) {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<cardItemModel>()
        for (i in 0 until data!!.subCategoryImg.size) {
            val dumy = data.subCategoryImg[i]
            Log.d("instantDelete", "dialogHomeScreen:${dumy.productImg} ")
            arrayList.add(
                cardItemModel(
                    dumy.id, dumy.productImg, dumy.productName,
                    Integer.parseInt(dumy.discountedPrice), Integer.parseInt(dumy.priceShow)
                )
            )
        }

        GlobalScope.launch(Dispatchers.Main) {

            val adapter = adapterCategories(applicationContext, arrayList)
            simpleCategories.adapter = adapter

        }

        dialog.show()
    }

    // onclick data send to single activity
    private fun dialogCategories(data: mainCategoryModel) {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<CategoryImg>()
        for (i in 0 until data.categoryImg.size) {
            val dumy = data.categoryImg[i]
            Log.d("instantDelete", "getView: ${dumy.categoryImg}")
            arrayList.add(

                CategoryImg(
                    dumy.categoryImg,
                    dumy.categoryName,
                    dumy.categoryStatus,
                    dumy.id,
                    dumy.discountPrice,
                    dumy.price
                )
            )
        }

        GlobalScope.launch(Dispatchers.Main) {

            val adapter = adapterCategoryHome(intentItemTrending,applicationContext, arrayList, currentUserLogin)
            simpleCategories.adapter = adapter
        }

        dialog.show()
    }

    private fun brandFocusBottom() {
        val arrayList = ArrayList<bannerBrandModel>()

        arrayList.add(bannerBrandModel(R.drawable.food_offer))
        arrayList.add(bannerBrandModel(R.drawable.babycare_offer))
        arrayList.add(bannerBrandModel(R.drawable.beauty_banner))
        arrayList.add(bannerBrandModel(R.drawable.healtynuts_offer))

        binding.rvBrandFocusBottom.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val arrayAdapter = adapterBanner(arrayList, this)

        binding.rvBrandFocusBottom.adapter = arrayAdapter
    }

    private fun brandFocusTop() {
        val arrayList = ArrayList<bannerBrandModel>()

        arrayList.add(bannerBrandModel(R.drawable.food_offer))
        arrayList.add(bannerBrandModel(R.drawable.babycare_offer))
        arrayList.add(bannerBrandModel(R.drawable.beauty_banner))
        arrayList.add(bannerBrandModel(R.drawable.healtynuts_offer))

        binding.rvBrandFocus.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val arrayAdapter = adapterBanner(arrayList, this)

        binding.rvBrandFocus.adapter = arrayAdapter
    }

    private fun getTrendingItem() {
        Log.d("trendingTag", "getTrendingItem: ")

        GlobalScope.launch {
            val call = repo.getMainTrending()
            Log.d("trendingTag", "getTrendingItem: $call")
            if (call.isSuccessful) {
                trendingCateDialogData = call.body()!!
                Log.d("trendingTag", "getTrendingItem:${Gson().toJson(call.body())} ")
                tendingItem(call.body())
            } else
                Log.d("trendingTag", "getTrendingItem:${call.errorBody()} ")
        }
    }

    private fun hitMainCategoryApi() {
        GlobalScope.launch {
            val call = repo.getMainCategory()
            if (call.isSuccessful) {
                mainCateDialogData = call.body()!!
                val gson = Gson()
                Log.d("apiData", "hitMainCategoryApi:  Success${gson.toJson(call.body()!!)}")
                populatingData(call.body()!!)
            } else
                Log.d("apiData", "hitMainCategoryApi: error ${call.errorBody()}")
        }
    }

    private fun populatingData(data: mainCategoryModel) {
        val arrayData = ArrayList<CategoryImg>()
        for (i in 0 until data.categoryImg.size) {
            val dumy = data.categoryImg[i]
            if (dumy.categoryStatus == 1) {
                arrayData.add(
                    CategoryImg(
                        dumy.categoryImg,
                        dumy.categoryName,
                        dumy.categoryStatus,
                        dumy.id,
                        "888",
                        "989"
                    )
                )
            } else {
                Log.d("rawt", "populatingData:${data.categoryImg}  ")
            }
        }

        runOnUiThread {
            val adapter = adapterCategoryHome(intentItemTrending,this, arrayData, currentUserLogin)
            binding.rvCategoryHome!!.adapter = adapter
        }
    }

    override fun onStart() {
        super.onStart()
        when {
            PermissionUtils.isAccessFineLocationGranted(this) -> {
                when {
                    PermissionUtils.isLocationEnabled(this) -> {
                        setUpLocationListener()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(this)
                    }
                }
            }
            else -> {
                PermissionUtils.requestAccessFineLocationPermission(
                    this@HomeActivity,
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
        updateTotal(currentUserLogin)
    }

    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // for getting the current location update after every 2 seconds with high accuracy
        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {

                        Log.d("kk", "onLocationResult:${location.latitude} ${location.longitude} ")

                        val geoCoder = Geocoder(this@HomeActivity, Locale.getDefault())
                        val addressList =
                            geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                        supportActionBar?.title = addressList[0].getAddressLine(0) +
                                "," + addressList[0].countryName
                        Log.d(
                            "kk", "onLocationResult: ${addressList[0].getAddressLine(0)} +\n" +
                                    "                                \",\" +${addressList[0].countryName}"
                        )
                    }
                }
            },
            Looper.myLooper()
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(this) -> {
                            setUpLocationListener()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(this)
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.location_permission_not_granted),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        super.onBackPressed()
    }

    private fun updateTotal(action: String) {

        var reto = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = reto.getCartDetail(action)
            if (call.isSuccessful) {
                binding.tvCartCount.text = call.body()!!.categoryImg.size.toString()
            } else
                Toasty.getToasty(applicationContext, "${call.errorBody()}")
        }
    }

    private fun hitPostCartTrending(name: String, price: Int, img: String) {

        val currentUser = RequestBody.create("text/plain".toMediaTypeOrNull(), currentUserLogin)
        val itemNo = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val cartItem = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val cartPrice = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val itemImgUrl = RequestBody.create("text/plain".toMediaTypeOrNull(), img)

        val retro = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val call = retro.postCartDetail(currentUser, cartItem, itemNo, cartPrice, itemImgUrl)
            if (call.isSuccessful) {
                Toasty.getToasty(applicationContext, call.body()!!.message)
            } else
                Toasty.getToasty(applicationContext, "${call.errorBody()!!}")
        }
    }
}