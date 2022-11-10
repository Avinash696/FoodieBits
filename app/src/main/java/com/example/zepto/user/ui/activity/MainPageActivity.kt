package com.example.zepto.user.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.zepto.Application.application
import com.example.zepto.R
import com.example.zepto.Repositary.Repositary
import com.example.zepto.databinding.ActivityMainPageBinding
import com.example.zepto.user.viewModels.MainPageViewModel
import com.example.zepto.user.viewModels.MainPageViewModelFactory

class MainPageActivity : AppCompatActivity() {
    private lateinit var mainPageViewModel: MainPageViewModel
    private lateinit var binding:ActivityMainPageBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_page)
        val repo =(application as application).repositary
        trending(repo)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun trending(repo:Repositary){
        mainPageViewModel = ViewModelProvider(this, MainPageViewModelFactory(this,repo))[MainPageViewModel::class.java]
        mainPageViewModel.repoTrendingVMLiveData.observe(this) {
            Log.d("MainPage", "trending: $it")
        }
    }
}