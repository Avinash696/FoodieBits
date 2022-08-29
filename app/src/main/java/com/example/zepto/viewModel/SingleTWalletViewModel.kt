package com.example.zepto.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingleTWalletViewModel(val amount:Int) :
    ViewModel() {

    var countMutableLiveData = MutableLiveData<Int>()
    var arrayNameSingle = MutableLiveData<ArrayList<String>>()
    var arrayAmountSingle = MutableLiveData<ArrayList<Int>>()
    var arrayImageSingle = MutableLiveData<ArrayList<Int>>()

    var itemCount = 0
    var totalAmount = 0

    fun itemCountInc() {
        itemCount++
    }

    fun itemCountDec() {
        itemCount--
    }

    fun totalAmount() {
        totalAmount = itemCount * amount
    }
    private fun setArrayData(){
        //need to set data
        countMutableLiveData.value = amount
    }

}