package com.example.zepto.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemCountViewModel : ViewModel() {
    var countMutableLiveData = MutableLiveData<Int>(0)
    var arrayName = MutableLiveData<ArrayList<String>>()
    var amountName = MutableLiveData<ArrayList<Int>>()
    var imageName = MutableLiveData<ArrayList<Int>>()
    var count: Int = 0


    fun itemCountInc() {
        count++
    }

    fun itemCountDec() {
        count--
    }

    fun setArrayData() {}

    fun setCount() {
        countMutableLiveData.value = count
    }
    fun setCounter (){
        count = countMutableLiveData.value!!
    }
}