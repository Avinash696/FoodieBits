package com.example.zepto.user.viewModels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.zepto.Repositary.Repositary

class MainPageViewModelFactory(val context:Context, val repositary: Repositary) :ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainPageViewModel(context,repositary) as T
    }
}