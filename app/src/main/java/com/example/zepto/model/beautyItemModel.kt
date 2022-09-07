package com.example.zepto.model

import java.io.Serializable

data class beautyItemModel(
    val listImg: Int,
    val listName: String,
    var discount: Int,
    override val img: Int,
    override var name: String,
    override val discountPrice: Int,
    override var Price: Int
) :
    cardItemWithoutId(discount, img, name, discountPrice, Price),Serializable {
}