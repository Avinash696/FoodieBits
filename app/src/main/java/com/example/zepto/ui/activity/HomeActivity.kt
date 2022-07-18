package com.example.zepto.ui.activity


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.adapter.adapterBanner
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.databinding.ActivityHomeBinding
import com.example.zepto.model.bannerBrandModel
import com.example.zepto.model.beautyItemModel
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.listCategory
import com.example.zepto.ui.fragment.SecondFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvTrending: RecyclerView
    private lateinit var simpleCategories: GridView
    private lateinit var bottomNavHome: BottomNavigationView

    //appbar
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    lateinit var appBar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //location fused
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //cart countt
    val count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
        showImg()
        tendingItem()
        catalogItem()
//        catalogGridView()
        locationPermission()
        brandFocusBottom()
        brandFocusTop()

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
            dialogHomeScreen()
        }
        //categories
        binding.tvCategoriesSeeAll.setOnClickListener {
            dialogCategories()
        }

        //categories on click
        binding.llBeautyProduct.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",1)
            startActivity(intent)
        }
        binding.llInstantFood.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",2)
            startActivity(intent)
        }
        binding.llColdDrink.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",3)
            startActivity(intent)
        }
        binding.llBiscuit.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",4)
            startActivity(intent)
        }
        binding.llChoco.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",5)
            startActivity(intent)
        }
        binding.llMasala.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",6)
            startActivity(intent)
        }
        binding.llOil.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",7)
            startActivity(intent)
        }
        binding.llSauce.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",8)
            startActivity(intent)
        }
        binding.llCoffee.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",9)
            startActivity(intent)
        }
        binding.llGreenTea.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",10)
            startActivity(intent)
        }
        binding.llCleaningEssence.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",11)
            startActivity(intent)
        }
        binding.llTea.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this,DetailActivity::class.java)
//            val beautyKey = beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120)
            intent.putExtra("beautyKey",12)
            startActivity(intent)
        }
    }
    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flDetail, fragment)
        fragmentTransaction.commit()
    }

    private fun setSideNavBar() {
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_refund -> {
                    Toast.makeText(this, "Refund", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_order -> {
                    startActivity(Intent(this, OrderListActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
//                    startActivity(Intent(this,CustomerProfileActivity::class.java))
                    true
                }
                R.id.menu_faq -> {
                    startActivity(Intent(this, FaqsActivity::class.java))
                    true
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
//        simpleCategories = binding.simpleView
        bottomNavHome = binding.bottomNavigation


        //fused initilized
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }

    private fun bottomNav() {
        bottomNavHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.menu_category -> {
                    startActivity(Intent(this, AdminActivity::class.java))
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

    private fun showImg() {
        val handler = Handler()
        val r = Runnable {
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        handler.postDelayed(r, 5000)
    }

    private fun tendingItem() {

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.f1, "Maggie", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.biscut, "Biscuits", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.colddrink, "Drinks", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.egg, "Eggs", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Fruit Vegetable", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.munch, "Munch", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.surf, "Surf", 2, 3))

        rvTrending.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val arrayAdapter = adapterTrending(arrayList, this)
        rvTrending.adapter = arrayAdapter
    }

    private fun catalogItem() {
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))

//        rvCategories.layoutManager = LinearLayoutManager(this)
//        val arrayAdapter = adapterTrending(arrayList)
//        rvCategories.adapter = arrayAdapter
    }

//    private fun catalogGridView() {
//        val arrayList = ArrayList<cardItemModel>()
//        arrayList.add(cardItemModel(1, R.drawable.beauty, "Beauty", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.colddrink, "Cold Drink", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.biscut, "Biscuts", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//
//        val adapter = adapterCategories(this, arrayList)
//        simpleCategories.adapter = adapter
//    }

    //specific right menu
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.home_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

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

//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_refund -> Toast.makeText(this, "Refund Clicked", Toast.LENGTH_SHORT).show()
//            R.id.menu_order -> startActivity(Intent(this, AdminActivity::class.java))
//            R.id.menu_category -> startActivity(Intent(this, AdminActivity::class.java))
//            R.id.menu_home -> startActivity(Intent(this, AdminActivity::class.java))
//        }
//        return true
//    }


    private fun locationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                2
            )
        }
    }

    private fun showLocation() {
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
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            val location = it.result
            if (location != null) {
                val geoCoder = Geocoder(this, Locale.getDefault())
                val addressList = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                supportActionBar?.title = addressList[0].getAddressLine(0) +
                        "," + addressList[0].countryName

                Log.d(
                    "myloc",
                    "showLocation: ${addressList[0].latitude} \n ${addressList[0].getAddressLine(0)}" +
                            "\n ${addressList[0].locality}   \n ${addressList[0].countryName}"
                )
            } else {
                Log.d("myloc", "showLocation: Location Null buddy")
            }
        }
    }

    private fun dialogHomeScreen() {
        val dialog = Dialog(this)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.colddrink, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.biscut, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))

        val adapter = adapterCategories(this, arrayList)
        simpleCategories.adapter = adapter

        dialog.show()
    }
    private fun dialogCategories() {
        val dialog = Dialog(this)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.by1, "Beauty", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.instant1, "Instant Food", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.cd1, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.biscut, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.c1, "Choco", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.m1, "Masala", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.oil1, "Oil", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.s1, "Sauce", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.coffee0, "Coffee", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.gt1, "Green Tea", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.tea1, "Tea ", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.clean_item, "Cleaning Essential", 2, 3))

        val adapter = adapterCategories(this, arrayList)
        simpleCategories.adapter = adapter

        dialog.show()
    }
    private fun brandFocusBottom(){
        val arrayList = ArrayList<bannerBrandModel>()
        arrayList.add(bannerBrandModel( R.drawable.offer1))
        arrayList.add(bannerBrandModel( R.drawable.offer2))
        arrayList.add(bannerBrandModel( R.drawable.offer3))
        arrayList.add(bannerBrandModel( R.drawable.offer5))
        arrayList.add(bannerBrandModel( R.drawable.offer6))

        binding.rvBrandFocusBottom.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val arrayAdapter = adapterBanner(arrayList, this)
        binding.rvBrandFocusBottom.adapter = arrayAdapter
    }
    private fun brandFocusTop(){
        val arrayList = ArrayList<bannerBrandModel>()
        arrayList.add(bannerBrandModel( R.drawable.offer10))
        arrayList.add(bannerBrandModel( R.drawable.offer6))
        arrayList.add(bannerBrandModel( R.drawable.offer7))
        arrayList.add(bannerBrandModel( R.drawable.offer8))
        arrayList.add(bannerBrandModel( R.drawable.offer9))

        binding.rvBrandFocus.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val arrayAdapter = adapterBanner(arrayList, this)
        binding.rvBrandFocus.adapter = arrayAdapter
    }
    private fun someDummyArray(){
        val beautyArrDemo = arrayListOf<listCategory>()
//        beautyArrDemo.add(listCategory(R.drawable.b10, "All Item"))
//        beautyArrDemo.add(listCategory(R.drawable.b1, "Face Cream"))
//        beautyArrDemo.add(listCategory(R.drawable.b2, "Body Cream"))
//        beautyArrDemo.add(listCategory(R.drawable.b3, "Hair Cream "))
//        beautyArrDemo.add(listCategory(R.drawable.b4, "eye Cream"))
//        beautyArrDemo.add(listCategory(R.drawable.b5, "Nose Cream"))
//        beautyArrDemo.add(listCategory(R.drawable.b6, "Cream"))
//
//        val beautySubList = arrayListOf<cardItemModel>()
//        beautySubList.add(cardItemModel(1, R.drawable.b1, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b2, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b3, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b4, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b5, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b6, "Beauty", 197, 120))
//        beautySubList.add(cardItemModel(1, R.drawable.b7, "Beauty", 197, 120))
//
//        val beautyMainList = ArrayList<List<cardItemModel>>()
//        beautyMainList.add(0,beautySubList)
//        beautyMainList.add(1,beautySubList)
//        beautyMainList.add(1,beautySubList)
//        beautyMainList.add(1,beautySubList)
//        beautyMainList.add(1,beautySubList)
//        beautyMainList.add(1,beautySubList)
//        beautyMainList.add(1,beautySubList)

    val beautyTestData = ArrayList<beautyItemModel>()

        beautyTestData.add(beautyItemModel(R.drawable.b10,"All Item",1, R.drawable.b1, "Beauty1", 197, 120))
        beautyTestData.add(beautyItemModel(R.drawable.b1,"Face Cream",1, R.drawable.b2, "Beauty2", 197, 120))
        beautyTestData.add(beautyItemModel(R.drawable.b2,"Body Cream",1, R.drawable.b3, "Beauty3", 197, 120))
        beautyTestData.add(beautyItemModel(R.drawable.b3,"Hair Cream",1, R.drawable.b4, "Beauty4", 197, 120))
        beautyTestData.add(beautyItemModel(R.drawable.b4,"eye Cream",1, R.drawable.b5, "Beauty5", 197, 120))
        beautyTestData.add(beautyItemModel(R.drawable.b5,"Nose Cream",1, R.drawable.b6, "Beauty6", 197, 120))


    }
}