package com.example.viewModel

import androidx.lifecycle.ViewModel

class SingleTWalletViewModel(private val amount: Int) : ViewModel() {

    var  itemCount = 0
    var totalAmount = 0

    fun itemCountInc(){
        itemCount ++
    }
    fun itemCountDec(){
        itemCount --
    }
    fun totalAmount(){
      totalAmount = itemCount * amount
    }

}