package com.example.zepto.Admin.ui.activity

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
import com.example.zepto.databinding.ActivityAdminHomeBinding
import com.example.zepto.ui.fragment.CoplainBoxFragment
import com.example.zepto.ui.fragment.DashboardAdminFragment
import com.example.zepto.ui.fragment.DashboardFragment
import com.google.android.material.navigation.NavigationView

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var nvAdminHome: NavigationView
    private lateinit var binding: ActivityAdminHomeBinding
    //appbar
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home)

        init()
        changeFragment(CategroiesFragment())

        //action
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
                    changeFragment(AddUserFragment())
                    Log.d("myAdmin", "clicked Add User")
                    binding.dlAdminHome.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_adminCategories -> {
                    Log.d("myAdmin", "clicked Cat")
                    changeFragment(CategroiesFragment())
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