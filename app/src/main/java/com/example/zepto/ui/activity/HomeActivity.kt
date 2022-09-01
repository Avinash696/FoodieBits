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
import com.example.zepto.viewModel.ItemCountViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterBanner
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.databinding.ActivityHomeBinding
import com.example.zepto.model.bannerBrandModel
import com.example.zepto.model.cardItemModel
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
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //cart count
    val count = 0

    //intent send data 3 var
    var intentName: ArrayList<String> = ArrayList<String>()
    var intentAmount: ArrayList<Int> = ArrayList<Int>()
    var intentImg: ArrayList<Int> = ArrayList<Int>()

    //viewmodel cout
    private lateinit var countViewModel: ItemCountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
        showImg()

        locationPermission()
        brandFocusBottom()
        brandFocusTop()

        //viewModel
        countViewModel = ViewModelProvider(this)[ItemCountViewModel::class.java]
        tendingItem()
        countViewModel.countMutableLiveData.observe(this) {
            binding.tvCartCount.text = it.toString()
        }
        //getttog all values namearray , amount array and arraya key  in viewmdoel
        //viewmdpel cant be used in 2 aciviies
        //either create  2 array lost or array list in viewmdoel

        //viewmodel 3 var observe

        countViewModel.arrayName.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentName.add(it[i])
            }
        }

        countViewModel.imageName.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentImg.add(it[i])
            }
        }
        countViewModel.amountName.observe(this) {
            for (i in 0 until it.size) {
                Log.d("buddy", "onCreate: ${it[i]}")
                intentAmount.add(it[i])
            }
        }
        binding.tvCartCount.text = countViewModel.count.toString()

        //counter update
        binding.tvCartCount.text = countViewModel.toString()

        //cart
        binding.llCart.setOnClickListener {
            Log.d("goblin", "onCreate:$intentName $intentAmount $intentImg ")
            if (intentName != null) {
                val intent = Intent(this, CartActivity::class.java)
                intent.putExtra("nameArray", intentName)
                intent.putExtra("amountArray", intentAmount)
                intent.putExtra("imgArray", intentImg)
                startActivity(intent)
            } else {
                Toast.makeText(this, "No Item in Cart", Toast.LENGTH_SHORT).show()
            }
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
            dialogHomeScreen()
        }
        //categories
        binding.tvCategoriesSeeAll.setOnClickListener {
            dialogCategories()
        }

        //categories on click
        binding.llBeautyProduct.setOnClickListener {
            countViewModel.setCounter()
            Log.d("tares", "onCreate: $intentName $intentImg $intentAmount ")
//            someDummyArray()
//            Log.d("hhhh", "onCreate: ${countViewModel.count}")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)////
            intent.putExtra("beautyKey", 1)
            intent.putExtra("nameArrayKey",intentName)
            intent.putExtra("amountArrayKey",intentAmount)
            intent.putExtra("imgArrayKey",intentImg)
            intent.putStringArrayListExtra("nameArrayKey", intentName)
            startActivity(intent)
        }
        binding.llInstantFood.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 2)
            startActivity(intent)
        }
        binding.llColdDrink.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 3)
            startActivity(intent)
        }
        binding.llBiscuit.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 4)
            startActivity(intent)
        }
        binding.llChoco.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 5)
            startActivity(intent)
        }
        binding.llMasala.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 6)
            startActivity(intent)
        }
        binding.llOil.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 7)
            startActivity(intent)
        }
        binding.llSauce.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 8)
            startActivity(intent)
        }
        binding.llCoffee.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 9)
            startActivity(intent)
        }
        binding.llGreenTea.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 10)
            startActivity(intent)
        }
        binding.llCleaningEssence.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 11)
            startActivity(intent)
        }
        binding.llTea.setOnClickListener {
//            someDummyArray()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("counterKey", countViewModel.count)
            intent.putExtra("beautyKey", 12)
            startActivity(intent)
        }
    }

    private fun setSideNavBar() {
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_allCategories -> {
                    dialogCategories()
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
//      simpleCategories = binding.simpleView
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
        val arrayAdapter = adapterTrending(arrayList, this, countViewModel)
        rvTrending.adapter = arrayAdapter
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

//                val addressList = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
//                supportActionBar?.title = addressList[0].getAddressLine(0) +
//                        "," + addressList[0].countryName
//                Log.d(
//                    "myloc",
//                    "showLocation: ${addressList[0].latitude} \n ${addressList[0].getAddressLine(0)}" +
//                            "\n ${addressList[0].locality}   \n ${addressList[0].countryName}"
//                )

            } else {
                Log.d("myloc", "showLocation: Location Null buddy")
            }
        }
    }

    private fun dialogHomeScreen() {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<cardItemModel>()

        arrayList.add(cardItemModel(12, R.drawable.f1, "Maggie", 2, 3))
        arrayList.add(cardItemModel(11, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(21, R.drawable.biscut, "Biscuits", 2, 3))
        arrayList.add(cardItemModel(31, R.drawable.colddrink, "Drinks", 2, 3))
        arrayList.add(cardItemModel(41, R.drawable.egg, "Eggs", 2, 3))
        arrayList.add(cardItemModel(51, R.drawable.fruit_vegitable, "Fruit Vegetable", 2, 3))
        arrayList.add(cardItemModel(61, R.drawable.munch, "Munch", 2, 3))
        arrayList.add(cardItemModel(71, R.drawable.surf, "Surf", 2, 3))
        arrayList.add(cardItemModel(17, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(15, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(14, R.drawable.colddrink, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(13, R.drawable.biscut, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(21, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(11, R.drawable.fruit_vegitable, "Pizza", 2, 3))

        val adapter = adapterCategories(this, arrayList,countViewModel)
        simpleCategories.adapter = adapter

        dialog.show()
    }
    // onclick data send to single activity
    private fun dialogCategories() {
        val dialog = Dialog(this, R.style.full_screen_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_trending_seeall)
        val simpleCategories = dialog.findViewById<GridView>(R.id.simpleView)

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(12, R.drawable.by1, "Beauty", 2, 3))
        arrayList.add(cardItemModel(11, R.drawable.instant1, "Instant Food", 2, 3))
        arrayList.add(cardItemModel(21, R.drawable.cd1, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(31, R.drawable.biscut, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(41, R.drawable.c1, "Choco", 2, 3))
        arrayList.add(cardItemModel(51, R.drawable.m1, "Masala", 2, 3))
        arrayList.add(cardItemModel(61, R.drawable.oil1, "Oil", 2, 3))
        arrayList.add(cardItemModel(71, R.drawable.s1, "Sauce", 2, 3))
        arrayList.add(cardItemModel(17, R.drawable.coffee0, "Coffee", 2, 3))
        arrayList.add(cardItemModel(15, R.drawable.gt1, "Green Tea", 2, 3))
        arrayList.add(cardItemModel(14, R.drawable.tea1, "Tea ", 2, 3))
        arrayList.add(cardItemModel(13, R.drawable.clean_item, "Home Clean", 2, 3))

        val adapter = adapterCategories(this, arrayList,countViewModel)
        simpleCategories.adapter = adapter

        dialog.show()
    }

    private fun brandFocusBottom() {
        val arrayList = ArrayList<bannerBrandModel>()
//        arrayList.add(bannerBrandModel(R.drawable.cleaning_offer))
//        arrayList.add(bannerBrandModel(R.drawable.choclate_offer))
//        arrayList.add(bannerBrandModel(R.drawable.drink_offer))
//        arrayList.add(bannerBrandModel(R.drawable.food_offer))

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

}