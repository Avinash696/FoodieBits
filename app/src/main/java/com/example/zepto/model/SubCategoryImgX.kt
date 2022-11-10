package com.example.zepto.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "trendingTable")
data class SubCategoryImgX(
    val idUser: String,
    val productImg: String,
    val productName: String,
    val productQty: String,
@PrimaryKey
    val id: String,
    val discountedPrice: String,
    val priceShow: String
): Parcelable



