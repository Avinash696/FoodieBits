package com.example.zepto.user.viewModels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zepto.Repositary.Repositary
import com.example.zepto.model.SubCategoryImgX
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
class MainPageViewModel(context:Context, val repositary: Repositary):ViewModel() {
    init {
        viewModelScope.launch {
//            repositary.getTrendingItem(context)
        }
    }
    val repoTrendingVMLiveData :LiveData<List<SubCategoryImgX>>
        get() = repositary.repoTrendingItem
}