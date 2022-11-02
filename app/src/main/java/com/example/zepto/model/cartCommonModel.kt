package com.example.zepto.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class cartCommonModel(
    var id: String,
    val img: String,
    var name: String,
    val itemCount: Int,
    var Price: Int
) : Parcelable