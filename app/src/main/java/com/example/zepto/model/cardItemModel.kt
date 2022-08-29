package com.example.zepto.model

import android.media.Image
import java.io.Serializable

open class cardItemModel(
    open var discount: Int,
    open val img: Int,
    open var name: String,
    open val discountPrice: Int,
    open var Price: Int,
) :Serializable{
}