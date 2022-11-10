package com.example.zepto.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zepto.model.SubCategoryImgX
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cartCommonModel

class DetailViewModel : ViewModel() {

    var arrayCategory = ArrayList<cardItemModel>()
    private val arrayCategoryLiveData = MutableLiveData<ArrayList<cardItemModel>>()
    var arrayCatData: LiveData<ArrayList<cardItemModel>> = arrayCategoryLiveData

    fun setCatItem(item: cardItemModel) {
        arrayCategory.clear()
        arrayCategory.add(item)
        arrayCategoryLiveData.postValue(arrayCategory)
    }

    private var arrayTrending = ArrayList<SubCategoryImgX>()

    fun setTrending(data: ArrayList<SubCategoryImgX>) {
        arrayTrending.addAll(data)
    }

    //combine both
    private val arrayCombine = ArrayList<cartCommonModel>()
    private val arrayMutableLiveData = MutableLiveData<ArrayList<cartCommonModel>>()
    val arrayLiveCommonData: LiveData<ArrayList<cartCommonModel>> = arrayMutableLiveData
    fun combineBoth(arrayCategoryParm:ArrayList<cardItemModel>,arrayTrendingParm:ArrayList<SubCategoryImgX>) {
        for (i in arrayCategoryParm) {
            arrayCombine.add(cartCommonModel(i.id, i.img, i.name, i.itemCount, i.Price))
        }

        for (j in arrayTrendingParm){
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
}