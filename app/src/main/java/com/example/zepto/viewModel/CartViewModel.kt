package com.example.zepto.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

     val  cartName  = ArrayList<String>()
     val  cartAmount  = ArrayList<Int>()
     val  cartImage = ArrayList<String>()

    fun setArray(nameItem:ArrayList<String>,amountItem:ArrayList<Int>,imageItem:ArrayList<String>){
        cartName.addAll(nameItem)
        cartAmount.addAll(amountItem)
        cartImage.addAll(imageItem)
    }
}