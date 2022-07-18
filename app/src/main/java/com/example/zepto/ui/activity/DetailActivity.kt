package com.example.zepto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.zepto.R
import com.example.zepto.adapter.adapterListDetail
import com.example.zepto.databinding.ActivityDetailBinding
import com.example.zepto.model.beautyItemModel
import com.example.zepto.model.listCategory
import com.example.zepto.ui.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    //    private lateinit var listAdapter: ArrayAdapter<listCategory>
    private lateinit var listDetailAdapter: adapterListDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        changeFragment(SecondFragment())
//        changeFragment(MapsFragment())
        val receiveIntent = intent
        val beautyIntent = receiveIntent.getIntExtra("beautyKey", 0)
        Log.d("myrules", "onCreate: $beautyIntent")
        listViewCategory()
        populatinDataFragment(beautyIntent)

        //bottom nav
        binding.botttomNavDetail.findViewById<BottomNavigationView>(R.id.bottomNavigationGeneral)
            .setOnItemSelectedListener {
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

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flDetail, fragment)
        fragmentTransaction.commit()
    }

    private fun listViewCategory() {
        val arr = ArrayList<listCategory>()
//        beautyItemModel.listImg
        arr.add(listCategory(R.drawable.f1, "All Item"))
        arr.add(listCategory(R.drawable.f2, "Watermelon"))
        arr.add(listCategory(R.drawable.f3, "Fruits"))
        arr.add(listCategory(R.drawable.f4, "Spinach "))
        arr.add(listCategory(R.drawable.f5, "Tomato"))

//        listAdapter = ArrayAdapter(this ,R.layout.detail_row,arr)
        listDetailAdapter = adapterListDetail(this, arr)
        binding.lvCategory.adapter = listDetailAdapter

        binding.lvCategory.setOnItemClickListener { adapterView, view, position, l ->
            val ss = view.findViewById<TextView>(R.id.tvRowDetail)
            if (position == 0) {
                changeFragment(FiirstFragment())
            } else if (position == 1) {
                changeFragment(SecondFragment())
            } else if (position == 2) {
                changeFragment(ThirdFragment())
            } else if (position == 3) {
                changeFragment(FourthFragment())
            }
            Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
        }
    }

    private fun populatinDataFragment(data: Int) {

        if (data == 1) {
            //my default layout
            changeFragment(beauty2Fragment())

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
                        changeFragment(beauty1Fragment())
                    }
                    1 -> {
                        changeFragment(beauty2Fragment())
                    }
                    2 -> {
                        changeFragment(beauty4Fragment())
                    }
                    3 -> {
                        changeFragment(beauty3Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 2) {
            //my default layout
            changeFragment(InstantFood1Fragment())
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
                        changeFragment(InstantFood1Fragment())
                    }
                    1 -> {
                        changeFragment(InstantFood2Fragment())
                    }
                    2 -> {
                        changeFragment(InstantFood3Fragment())
                    }
                    3 -> {
                        changeFragment(InstantFood5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 3) {
            //my default layout
            changeFragment(CdFruitDrinkFragment())
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
                        changeFragment(CdWaterFragment())
                    }
                    1 -> {
                        changeFragment(CdFruitDrinkFragment())
                    }
                    2 -> {
                        changeFragment(CdSodaFragment())
                    }
                    3 -> {
                        changeFragment(CdMilkDrinkFragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 4) {
            //my default layout
            changeFragment(Biscuit2Fragment())
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
                        changeFragment(Biscuit1Fragment())
                    }
                    1 -> {
                        changeFragment(Biscuit2Fragment())
                    }
                    2 -> {
                        changeFragment(Biscuit3Fragment())
                    }
                    3 -> {
                        changeFragment(Biscuit4Fragment())
                    }
                    4 -> {
                        changeFragment(Biscuit5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 5) {
            //my default layout
            changeFragment(Choco2Fragment())
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
                        changeFragment(Choco1Fragment())
                    }
                    1 -> {
                        changeFragment(Choco2Fragment())
                    }
                    2 -> {
                        changeFragment(Choco3Fragment())
                    }
                    3 -> {
                        changeFragment(Choco4Fragment())
                    }
                    4 -> {
                        changeFragment(Choco5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 6) {
            changeFragment(Masala3Fragment())
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
                        changeFragment(Masala1Fragment())
                    }
                    1 -> {
                        changeFragment(Masala2Fragment())
                    }
                    2 -> {
                        changeFragment(Masala3Fragment())
                    }
                    3 -> {
                        changeFragment(Masala4Fragment())
                    }
                    4 -> {
                        changeFragment(Masala5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 7) {
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
                        changeFragment(Oil1Fragment())
                    }
                    1 -> {
                        changeFragment(Oil2Fragment())
                    }
                    2 -> {
                        changeFragment(Oil3Fragment())
                    }
                    3 -> {
                        changeFragment(Oil4Fragment())
                    }
                    4 -> {
                        changeFragment(Oil5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 8) {
            changeFragment(Sauce4Fragment())
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
                        changeFragment(Sauce1Fragment())
                    }
                    1 -> {
                        changeFragment(Sauce2Fragment())
                    }
                    2 -> {
                        changeFragment(Sauce3Fragment())
                    }
                    3 -> {
                        changeFragment(Sauce4Fragment())
                    }
                    4 -> {
                        changeFragment(Sauce5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 9) {
            changeFragment(Coffee2Fragment())
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
                        changeFragment(Coffee1Fragment())
                    }
                    1 -> {
                        changeFragment(Coffee2Fragment())
                    }
                    2 -> {
                        changeFragment(Coffee3Fragment())
                    }
                    3 -> {
                        changeFragment(Coffee4Fragment())
                    }
                    4 -> {
                        changeFragment(Coffee5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 10) {
            changeFragment(GreenTea3Fragment())
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
                        changeFragment(GreenTea1Fragment())
                    }
                    1 -> {
                        changeFragment(GreenTea2Fragment())
                    }
                    2 -> {
                        changeFragment(GreenTea3Fragment())
                    }
                    3 -> {
                        changeFragment(GreenTea4Fragment())
                    }
                    4 -> {
                        changeFragment(GreenTea5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 11) {
            changeFragment(CleaningEssentia5Fragment())
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
                        changeFragment(CleaningEssential1Fragment())
                    }
                    1 -> {
                        changeFragment(CleaningEssentia2Fragment())
                    }
                    2 -> {
                        changeFragment(CleaningEssentia3Fragment())
                    }
                    3 -> {
                        changeFragment(CleaningEssentia4Fragment())
                    }
                    4 -> {
                        changeFragment(CleaningEssentia5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
        else if (data == 12) {
            changeFragment(Tea2Fragment())
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
                        changeFragment(Tea1Fragment())
                    }
                    1 -> {
                        changeFragment(Tea2Fragment())
                    }
                    2 -> {
                        changeFragment(Tea3Fragment())
                    }
                    3 -> {
                        changeFragment(Tea4Fragment())
                    }
                    4 -> {
                        changeFragment(Tea5Fragment())
                    }
                }
                Log.d("apitest", "listViewCategory:$position ${adapterView[position]}")
            }
        }
    }

}