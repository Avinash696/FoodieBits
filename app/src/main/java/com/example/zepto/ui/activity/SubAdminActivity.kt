package com.example.zepto.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.zepto.*
import com.example.zepto.databinding.ActivitySubAdminBinding
import com.example.zepto.ui.fragment.DashboardFragment
import com.google.android.material.navigation.NavigationView

class SubAdminActivity : AppCompatActivity() {
    private lateinit var nvAdminHome: NavigationView
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    private lateinit var binding:ActivitySubAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sub_admin)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_admin)

//        when (operation) {
//            '+' -> printf("%.1lf + %.1lf = %.1lf", n1, n2, n1 + n2)
//            '-' -> printf("%.1lf - %.1lf = %.1lf", n1, n2, n1 - n2)
//            '*' -> printf("%.1lf * %.1lf = %.1lf", n1, n2, n1 * n2)
//            '/' -> printf("%.1lf / %.1lf = %.1lf", n1, n2, n1 / n2)
//            else -> printf("Error! operator is not correct")
//        }

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
                R.id.menu_SubadminDashboard ->{
                    changeFragment(DashboardFragment())
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu_SubadminAddUser -> {
                    changeFragment(AddUserFragment())
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