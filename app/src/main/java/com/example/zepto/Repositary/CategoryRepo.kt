package com.example.zepto.Repositary

import androidx.lifecycle.MutableLiveData
import com.example.test.aviInterface
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.mainCategoryModel

class CategoryRepo(val aviInterface: aviInterface) {

    var  categoryRepo = MutableLiveData<mainCategoryModel>()

    suspend fun getMainCategoryRepo(){
        val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
        val call = repo.getMainCategory()
        categoryRepo.value = call.body()
//        if(call.isSuccessful){
//
//        }
//        else
//            categoryRepo.value = call
    }

}