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
import com.example.zepto.*
import com.example.zepto.Admin.ui.retailerFragment.AddUserTestFragment
import com.example.zepto.Admin.ui.retailerFragment.RetailerDashboardFragment
import com.example.zepto.databinding.ActivityRetailerAdminBinding
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso

class RetailerAdminActivity : AppCompatActivity() {
    private lateinit var nvAdminHome: NavigationView
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityRetailerAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retailer_admin)
        init()
        changeFragment(RetailerDashboardFragment())

        //set title
        val intent = intent
        val title = intent.getStringExtra("retailerTitle")
        val titleImg = intent.getStringExtra("adminImg")!!

        val customCreatedUrl = "http://56testing.club/imgFolder/uploads/wlAdmin/$titleImg"
        Log.d("adminTitleCheck", "onCreate: $customCreatedUrl")
        binding.appBar.title = title
        //action
        Picasso.get().load(customCreatedUrl).into(binding.ivTitleLogoRetailer)
        setSupportActionBar(binding.appBar)
        actionBarDrawableToggle =
            ActionBarDrawerToggle(this, binding.dlAdminHome, R.string.nav_open, R.string.nav_close)
        actionBarDrawableToggle.syncState()

        //onclick
        nvAdminHome.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_SubadminDashboard -> {
                    changeFragment(RetailerDashboardFragment())
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminAddUser -> {
                    changeFragment(AddUserTestFragment())
                    Log.d("myAdmin", "clicked Add User")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminCategories -> {
                    Log.d("myAdmin", "clicked Cat")
                    changeFragment(CategroiesFragment())
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminTrending -> {
                    changeFragment(TrendingFragment())
                    Log.d("myAdmin", "clicked Trend")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminOrderMaster -> {
                    changeFragment(OrderMasterFragment())
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminUserMaster -> {
                    changeFragment(UserMasterFragment())
                    Log.d("myAdmin", "clicked Master")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminContact -> {
                    changeFragment(ContactFragment())
                    binding.dlAdminHome.close()
                    Log.d("myAdmin", "clicked Contact")
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminLogout -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
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