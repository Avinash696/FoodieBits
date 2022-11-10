package com.example.zepto.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "currentCartTable")
//data class cartResult(
//
//@PrimaryKey(autoGenerate = true)
//var columnId: Int,
//    var id: String,
//    val img: String,
//    var name: String,
//    val itemCount: Int,
//    var Price: Int
//)




@Entity(tableName = "currentCartTable")
data class cartResult(
    val idUser: String,
    val productImg: String,
    val productName: String,
    val productQty: String,
    @PrimaryKey
    val id: String,
    val discountedPrice: String,
    val priceShow: String
)

