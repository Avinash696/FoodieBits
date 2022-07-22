package com.example.zepto.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    var countMutableLiveData = MutableLiveData<Int>()
    var arrayNameDetail = MutableLiveData<ArrayList<String>>()
    var arrayAmountDetail = MutableLiveData<ArrayList<Int>>()
    var arrayImageDetail = MutableLiveData<ArrayList<Int>>()
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
}