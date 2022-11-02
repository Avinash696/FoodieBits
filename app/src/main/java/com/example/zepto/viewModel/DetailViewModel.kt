package com.example.zepto.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cartCommonModel

class DetailViewModel : ViewModel() {
//    private var arrayCategory  = MutableLiveData<ArrayList<cardItemModel>>()
//
//    val arrayCategoryLiveData  :LiveData<ArrayList<cardItemModel>>
//    get() = arrayCategory
//
//    //test
//    var arrayCat = ArrayList<cardItemModel>()
//    fun updateCat(item:cardItemModel){
//        arrayCat.clear()
//        arrayCat.add(item)
//        arrayCategory.postValue(arrayCat)
//    }

    private var arrayCategory = ArrayList<cardItemModel>()
    private val arrayCategoryLiveData = MutableLiveData<ArrayList<cardItemModel>>()
    var arrayCatData: LiveData<ArrayList<cardItemModel>> = arrayCategoryLiveData

    fun setCatItem(item: cardItemModel) {
        arrayCategory.add(item)
        arrayCategoryLiveData.postValue(arrayCategory)
    }

    private var arrayTrending = ArrayList<SubCategoryImgX>()
    private val arrayTrendingLiveData = MutableLiveData<ArrayList<SubCategoryImgX>>()
    var arrayTrendingData: LiveData<ArrayList<SubCategoryImgX>> = arrayTrendingLiveData

    fun setTrending(data: ArrayList<SubCategoryImgX>) {
        arrayTrending.addAll(data)
        arrayTrendingLiveData.postValue(data)
    }

    //combine both
    private val arrayCombine = ArrayList<cartCommonModel>()
    private val arrayMutableLiveData = MutableLiveData<ArrayList<cartCommonModel>>()
    val arrayLiveCommonData: LiveData<ArrayList<cartCommonModel>> = arrayMutableLiveData
    fun combineBoth() {
        for (i in arrayCategory) {
            arrayCombine.add(cartCommonModel(i.id, i.img, i.name, i.itemCount, i.Price))
        }

        for (j in arrayTrending)
            arrayCombine.add(
                cartCommonModel(
                    j.id,
                    j.productImg,
                    j.productName,
                    Integer.parseInt(j.productQty),
                    Integer.parseInt(j.priceShow)
                )
            )
        arrayMutableLiveData.postValue(arrayCombine)
    }
}