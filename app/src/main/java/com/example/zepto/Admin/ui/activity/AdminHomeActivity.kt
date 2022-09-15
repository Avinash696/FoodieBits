package com.example.zepto.Admin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test.aviInterface
import com.example.zepto.*
import com.example.zepto.Admin.ui.retailerFragment.AddUserTestFragment
import com.example.zepto.Repositary.CategoryRepo
import com.example.zepto.constant.constants
import com.example.zepto.databinding.ActivityAdminHomeBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.mainCategoryModel
import com.example.zepto.ui.fragment.CoplainBoxFragment
import com.example.zepto.ui.fragment.DashboardAdminFragment
import com.example.zepto.ui.fragment.DashboardFragment
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var nvAdminHome: NavigationView
    private lateinit var binding: ActivityAdminHomeBinding
    lateinit var titleCustom :String
    lateinit var titleImg :String


    //appbar
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home)
        init()
        changeFragment(DashboardAdminFragment())

        //title set
        val intent = intent
        titleCustom = intent.getStringExtra("adminTitle")!!
        titleImg = intent.getStringExtra("adminImg")!!
        constants.currentUserLogin = titleCustom



        val customCreatedUrl = "http://56testing.club/imgFolder/uploads/admins/$titleImg"
        Log.d("adminTitleCheck", "onCreate: $titleCustom ${constants.currentUserLogin}")
        binding.appBar.title = titleCustom

        //action
        Picasso.get().load(customCreatedUrl).into(binding.ivTitleLogo)
        setSupportActionBar(binding.appBar)
        actionBarDrawableToggle =
            ActionBarDrawerToggle(this, binding.dlAdminHome, R.string.nav_open, R.string.nav_close)
        actionBarDrawableToggle.syncState()

        //onclick
        nvAdminHome.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_adminDashboard ->{
                    changeFragment(DashboardAdminFragment())
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminAddUser -> {
//                    changeFragment(AddUserFragment())
                    changeFragment(AddUserTestFragment())
                    Log.d("myAdmin", "clicked Add User")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminCreateUser -> {
                    val intent  = Intent(this,CreateAdminActivity::class.java)
                    intent.putExtra("adminTitleCheck",titleCustom)
                    startActivity(intent)
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminCreateRetailer -> {
                    val intent  = Intent(this,CreateRetailerActivity::class.java)
                    intent.putExtra("adminTitleCheck",titleCustom)
                    startActivity(intent)
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminCategories -> {
                    Log.d("myAdmin", "clicked Cat")
                    changeFragment(CategroiesFragment(titleCustom))
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminTrending -> {
                    changeFragment(TrendingFragment())
                    Log.d("myAdmin", "clicked Trend")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminOrderMaster -> {
                    changeFragment(OrderMasterFragment())
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminUserMaster -> {
                    changeFragment(UserMasterFragment())
                    Log.d("myAdmin", "clicked Master")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminContact -> {
                    changeFragment(ContactFragment())
                    binding.dlAdminHome.close()
                    Log.d("myAdmin", "clicked Contact")
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminComplainBox -> {
                    changeFragment(CoplainBoxFragment())
                    binding.dlAdminHome.close()
                    Log.d("myAdmin", "clicked Contact")
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawableToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        nvAdminHome = binding.nvAdminHome
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragHostAdmin, fragment)
        fragmentTransaction.commit()
    }
}