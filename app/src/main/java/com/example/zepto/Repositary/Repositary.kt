package com.example.zepto.Repositary

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.NetworkUtils
import com.example.test.AviInterface
import com.example.zepto.database.CartDatabase
import com.example.zepto.database.cartResult
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardItemModel

class Repositary(val aviInterface: AviInterface, private val CommonDatabase: CartDatabase) {
    private val TAG = "cartRepo"
    private var repoCartLiveData = MutableLiveData<List<cartResult>>()
    val repoCartData: LiveData<List<cartResult>>
        get() = repoCartLiveData

    private var repoTrendingLiveData = MutableLiveData<List<SubCategoryImgX>>()
    val repoTrendingItem :LiveData<List<SubCategoryImgX>>
            get() = repoTrendingLiveData


    suspend fun getCartItem(){
        val result = aviInterface.getMainTrending()
        if(result.isSuccessful){
//            repoLiveData.postValue(result.body().subCategoryImg)

        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getTrendingItem(context:Context){
        val result = aviInterface.getMainTrending()
        if(result.isSuccessful){
            if(NetworkUtils.isNetWorkConnected(context)){
                repoTrendingLiveData.postValue(result.body()?.subCategoryImg)
//                CommonDatabase.getFromDatabase().addTrendingDatabaseItem(result.body()!!.subCategoryImg)
            }
            else {
//                val trendingData = CommonDatabase.getFromDatabase().getTrendingDatabase()
//                repoTrendingLiveData.postValue(trendingData)
            }
        }
    }

}