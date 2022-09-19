package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.adapter.adapterListDetail
import com.example.zepto.databinding.ActivityDetailBinding
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.model.listCategory
import com.example.zepto.ui.fragment.*
import com.example.zepto.viewModel.ItemCountViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    //    private lateinit var listAdapter: ArrayAdapter<listCategory>
    private lateinit var listDetailAdapter: adapterListDetail
    private lateinit var bottomNavHome: BottomNavigationView
    private lateinit var detailViewModel: DetailViewModel
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
//        val homeName = receiveIntent.getStringArrayListExtra("nameArrayKey")
//        val homeAmount = receiveIntent.getStringArrayListExtra("amountArrayKey")
//        val homeImg = receiveIntent.getIntegerArrayListExtra("imgArrayKey")
//
//        Log.d("pen", "onCreate: $homeAmount $homeName $homeImg ${homeName!!.size}")

        Log.d("detailCount", "onCreate: $counterIntent")
        //counter set
        detailViewModel.countMutableLiveData.value = counterIntent

        Log.d("myrules", "onCreate: $beautyIntent")
        listViewCategory()
        populatinDataFragment(beautyIntent)
        //
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
        detailViewModel.countMutableLiveData.observe(this){
            binding.tvCartCountDetail.text = it.toString()
        }

        //count on click
         binding.llCartDetail.setOnClickListener {
             val intent = Intent(this,CartActivity::class.java)
             intent.putExtra("nameArray",intentName)
             intent.putExtra("amountArray",intentAmount)
             intent.putExtra("imgArray",intentImg)
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

    private fun listViewCategory() {
        val arr = ArrayList<listCategory>()
        arr.add(listCategory(R.drawable.f1, "All Item"))
        arr.add(listCategory(R.drawable.f2, "Watermelon"))
        arr.add(listCategory(R.drawable.f3, "Fruits"))
        arr.add(listCategory(R.drawable.f4, "Spinach "))
        arr.add(listCategory(R.drawable.f5, "Tomato"))

//        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
        listDetailAdapter = adapterListDetail(this, arr)
        binding.lvCategory.adapter = listDetailAdapter

        binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
            when (position) {
                0 -> {
                    changeFragment(FiirstFragment(detailViewModel))
                }
                1 -> {
                    changeFragment(SecondFragment(detailViewModel))
                }
                2 -> {
                    changeFragment(ThirdFragment(detailViewModel))
                }
                3 -> {
                    changeFragment(FourthFragment(detailViewModel))
                }
            }
            Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
        }
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
//        val adapter = adapterCategories(this, arrayList )
//        simpleCategories.adapter = adapter

        dialog.show()
    }
    private fun populatinDataFragment(data: Int) {
        when (data) {
            1 -> {
                //my default layout
                changeFragment(beauty2Fragment(detailViewModel))

                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.b10, "All Item"))
                arr.add(listCategory(R.drawable.b2, "Watermelon"))
                arr.add(listCategory(R.drawable.b3, "Fruits"))
                arr.add(listCategory(R.drawable.b4, "Spinach "))
                arr.add(listCategory(R.drawable.f5, "Tomato"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(beauty1Fragment(detailViewModel))
                            Log.d("beauty1Log", "populatinDataFragment: ${detailViewModel.arrayNameDetail.value}")
                        }
                        1 -> {
                            changeFragment(beauty2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(beauty4Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(beauty3Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            2 -> {
                //my default layout
                changeFragment(InstantFood1Fragment(detailViewModel))
                Log.d("myddd", "populatingBeauty: $data")
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.instant1, "All Item"))
                arr.add(listCategory(R.drawable.instant10, "Food 1"))
                arr.add(listCategory(R.drawable.instant3, "Food 2"))
                arr.add(listCategory(R.drawable.instant4, "Food3 "))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant9, "Food 4"))
                arr.add(listCategory(R.drawable.instant4, "Food3 "))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(InstantFood1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(InstantFood2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(InstantFood3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(InstantFood5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            3 -> {
                //my default layout
                changeFragment(CdFruitDrinkFragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.colddrink, "All Item"))
                arr.add(listCategory(R.drawable.cd1, "Ornage"))
                arr.add(listCategory(R.drawable.cd2, "Fruit"))
                arr.add(listCategory(R.drawable.cd3, "Soda "))
                arr.add(listCategory(R.drawable.cd4, "Water"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(CdWaterFragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(CdFruitDrinkFragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(CdSodaFragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(CdMilkDrinkFragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            4 -> {
                //my default layout
                changeFragment(Biscuit2Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.biscut, "All Item"))
                arr.add(listCategory(R.drawable.biscuit1, "OAts"))
                arr.add(listCategory(R.drawable.biscuit2, "Fifty Fifty"))
                arr.add(listCategory(R.drawable.biscuit3, "Bounce "))
                arr.add(listCategory(R.drawable.biscuit4, "Parley-G"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Biscuit1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Biscuit2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Biscuit3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Biscuit4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Biscuit5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            5 -> {
                //my default layout
                changeFragment(Choco2Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.c1, "All Item"))
                arr.add(listCategory(R.drawable.c2, "Choco"))
                arr.add(listCategory(R.drawable.c3, "nachos"))
                arr.add(listCategory(R.drawable.c4, "namkeen "))
                arr.add(listCategory(R.drawable.c5, "popcorn"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Choco1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Choco2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Choco3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Choco4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Choco5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            6 -> {
                changeFragment(Masala3Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.s1, "All Item"))
                arr.add(listCategory(R.drawable.s2, "powder"))
                arr.add(listCategory(R.drawable.s3, "paste"))
                arr.add(listCategory(R.drawable.s4, "pickle "))
                arr.add(listCategory(R.drawable.s5, "mix spices"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Masala1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Masala2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Masala3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Masala4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Masala5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            7 -> {
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.oil1, "All Item Oil"))
                arr.add(listCategory(R.drawable.oil2, "Watermelon"))
                arr.add(listCategory(R.drawable.oil3, "Fruits"))
                arr.add(listCategory(R.drawable.oil4, "Spinach "))
                arr.add(listCategory(R.drawable.oil5, "Tomato"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Oil1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Oil2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Oil3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Oil4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Oil5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            8 -> {
                changeFragment(Sauce4Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.s1, "All Item"))
                arr.add(listCategory(R.drawable.s2, "Tomato"))
                arr.add(listCategory(R.drawable.s3, "Chilli"))
                arr.add(listCategory(R.drawable.s4, "Soya "))
                arr.add(listCategory(R.drawable.s5, "Mayo"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Sauce1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Sauce2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Sauce3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Sauce4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Sauce5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            9 -> {
                changeFragment(Coffee2Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.coffee0, "All Item"))
                arr.add(listCategory(R.drawable.coffee2, "Tea"))
                arr.add(listCategory(R.drawable.coffee3, "Coffee"))
                arr.add(listCategory(R.drawable.coffee4, "Green Tea "))
                arr.add(listCategory(R.drawable.coffee5, "Cold Coffee"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Coffee1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Coffee2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Coffee3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Coffee4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Coffee5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            10 -> {
                changeFragment(GreenTea3Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.gt1, "All Item"))
                arr.add(listCategory(R.drawable.gt2, "Cold Drink"))
                arr.add(listCategory(R.drawable.gt3, "Hot Drink"))
                arr.add(listCategory(R.drawable.gt4, "Green Tea "))
                arr.add(listCategory(R.drawable.gt5, "Tea"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(GreenTea1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(GreenTea2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(GreenTea3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(GreenTea4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(GreenTea5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            11 -> {
                changeFragment(CleaningEssentia5Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.s1, "All Item"))
                arr.add(listCategory(R.drawable.s2, "Claning Essential"))
                arr.add(listCategory(R.drawable.s3, "Claning Essential 2"))
                arr.add(listCategory(R.drawable.s4, "Claning Essential 3 "))
                arr.add(listCategory(R.drawable.s5, "Claning Essential 4"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(CleaningEssential1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(CleaningEssentia2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(CleaningEssentia3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(CleaningEssentia4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(CleaningEssentia5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
            12 -> {
                changeFragment(Tea2Fragment(detailViewModel))
                val arr = ArrayList<listCategory>()
    //        beautyItemModel.listImg
                arr.add(listCategory(R.drawable.tea1, "All Item"))
                arr.add(listCategory(R.drawable.tea2, "Soft Drink"))
                arr.add(listCategory(R.drawable.tea3, "Soda"))
                arr.add(listCategory(R.drawable.tea4, "Water "))
                arr.add(listCategory(R.drawable.tea5, "Lemon Flavour"))

    //        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
                listDetailAdapter = adapterListDetail(this, arr)
                binding.lvCategory.adapter = listDetailAdapter

                binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
                    val ss = view.findViewById<TextView>(R.id.tvRowDetail)
                    when (position) {
                        0 -> {
                            changeFragment(Tea1Fragment(detailViewModel))
                        }
                        1 -> {
                            changeFragment(Tea2Fragment(detailViewModel))
                        }
                        2 -> {
                            changeFragment(Tea3Fragment(detailViewModel))
                        }
                        3 -> {
                            changeFragment(Tea4Fragment(detailViewModel))
                        }
                        4 -> {
                            changeFragment(Tea5Fragment(detailViewModel))
                        }
                    }
                    Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
                }
            }
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