package com.example.zepto.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Insert
     fun addCartItem(cartItem: ArrayList<cartResult>)

    @Delete
    fun removeCartItem(cartItem: cartResult)

    @Query("SELECT * FROM currentCartTable")
    fun getCartItem(): List<cartResult>

    //trending
//    @Insert
//    fun addTrendingDatabaseItem(trendingItem :List<SubCategoryImgX>)
//
//    @Query("SELECT * FROM currentCartTable")
//    fun getTrendingDatabase():List<SubCategoryImgX>
}