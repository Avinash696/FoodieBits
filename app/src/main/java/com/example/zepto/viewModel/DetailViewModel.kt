package com.example.zepto.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zepto.model.cardItemModel

class DetailViewModel : ViewModel() {
//    var countMutableLiveData = MutableLiveData<Int>()
//    var arrayNameDetail = MutableLiveData<ArrayList<String>>()
//    var arrayAmountDetail = MutableLiveData<ArrayList<Int>>()
//    var arrayImageDetail = MutableLiveData<ArrayList<Int>>()
//    var count: Int = 0
//
//    fun itemCountInc() {
//        count++
//    }
//
//    fun itemCountDec() {
//        count--
//    }
//
//    fun setArrayData() {}
//    //called in adapterSubList
//    fun setCount() {
//        countMutableLiveData.value = count
//    }
//
//    fun updatingCount(){
//        count = countMutableLiveData.value!!
//    }

    private var arrayCategory  = MutableLiveData<ArrayList<cardItemModel>>()

    val arrayCategoryLiveData  :LiveData<ArrayList<cardItemModel>>
    get() = arrayCategory

    //test
    var arrayCat = ArrayList<cardItemModel>()
    fun updateCat(item:cardItemModel){
        arrayCat.clear()
        arrayCat.add(item)
        arrayCategory.postValue(arrayCat)
    }
}