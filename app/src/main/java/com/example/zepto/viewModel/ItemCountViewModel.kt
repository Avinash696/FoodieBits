package com.example.zepto.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zepto.database.CartDatabase
import com.example.zepto.database.cartResult
import com.example.zepto.model.SubCategoryImgX

//class ItemCountViewModel(private val cartDatabase : CartDatabase) : ViewModel() {
class ItemCountViewModel() : ViewModel() {

    private var itemArrayData = MutableLiveData<ArrayList<SubCategoryImgX>>()
    val itemTrendingLiveData: LiveData<ArrayList<SubCategoryImgX>>
        get() = itemArrayData
    private val arrayTrending = ArrayList<SubCategoryImgX>()


    fun setTrendingItem(item: SubCategoryImgX) {
        arrayTrending.clear()
        arrayTrending.add(item)
        itemArrayData.postValue(arrayTrending)
    }

    private val arrayCart = ArrayList<SubCategoryImgX>()
    private var itemArrayCart = MutableLiveData<ArrayList<SubCategoryImgX>>()
    val itemLiveCart: LiveData<ArrayList<SubCategoryImgX>>
        get() = itemArrayCart


    fun setCartItem(item: SubCategoryImgX){
        //yaha pe array bano
        arrayCart.add(item)
        itemArrayCart.postValue(arrayCart)
//        cartDatabase.getFromDatabase().addCartItem(itemArray)
    }
}