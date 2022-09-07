package com.example.zepto.model

import java.io.Serializable

open class cardItemFullModel(
    override var id: String,
    var myId: String,
    override val img: String,
    override var name: String,
    override val discountPrice: Int,
    val myDiscountPrice: String,
    override var Price: Int,
) :Serializable, cardItemModel( id ,img,name,discountPrice,Price) {
}

open class cardItemModel(open var id: String,
                         open val img: String,
                         open var name: String,
                         open val discountPrice: Int,
                         open var Price: Int)
open class cardItemWithoutId(
    open var id: Int,
    open val img: Int,
    open var name: String,
    open val discountPrice: Int,
    open var Price: Int
)