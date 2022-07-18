package com.example.zepto.model

import android.media.Image
import java.io.Serializable

open class cardItemModel(
    open val discount: Int,
    open val img: Int,
    open val name: String,
    open val discountPrice: Int,
    open val Price: Int,
) :Serializable{
}