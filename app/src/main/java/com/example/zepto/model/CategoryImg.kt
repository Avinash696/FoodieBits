package com.example.zepto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CategoryImg(
    val categoryImg: String,
    val categoryName: String,
    val categoryStatus: Int,
    val id: String,
    val discountPrice: String,
    val price: String,
)

//@Entity(tableName = "currentCartTable")
//data class CategoryImg(
//    @PrimaryKey(autoGenerate = true)
//    var columnId: Int,
//    val categoryImg: String,
//    val categoryName: String,
//    val categoryStatus: Int,
//    val id: String,
//    val discountPrice: String,
//    val price: String,
//)


//@Entity(tableName = "currentCartTable")
//data class CategoryImg(
//    @PrimaryKey(autoGenerate = true)
//    var columnId: Int,
//    var id: String,
//    val img: String,
//    var name: String,
//    val itemCount: Int,
//    var Price: Int
//)