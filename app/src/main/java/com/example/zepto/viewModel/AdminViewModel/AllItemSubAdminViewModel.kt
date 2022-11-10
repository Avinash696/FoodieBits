package com.example.zepto.viewModel.AdminViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllItemSubAdminViewModel : ViewModel() {

   private var subProductKeyMutableLiveData = MutableLiveData<String>()

//    val subProductKeyLiveData  = LiveData<String>()

    fun setKey(){
//        subProductKeyMutableLiveData.value = subProductKeyLiveData.value
    }
}