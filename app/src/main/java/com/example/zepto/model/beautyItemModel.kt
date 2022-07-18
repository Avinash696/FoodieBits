package com.example.zepto.model

import java.io.Serializable

data class beautyItemModel(
    val listImg: Int,
    val listName: String,
    override val discount: Int,
    override val img: Int,
    override val name: String,
    override val discountPrice: Int,
    override val Price: Int
) :
    cardItemModel(discount, img, name, discountPrice, Price),Serializable {
}