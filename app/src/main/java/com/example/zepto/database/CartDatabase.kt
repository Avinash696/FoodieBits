package com.example.zepto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zepto.model.SubCategoryImgX

@Database(entities = [cartResult::class], version = 1)
abstract class CartDatabase : RoomDatabase() {
//    abstract fun getCartItem(): CartDao
    abstract fun getFromDatabase(): CartDao

    companion object {
        private var INSTANCE: CartDatabase? = null
        fun getDatabase(context: Context): CartDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, CartDatabase::class.java, "cartDb")
                        .allowMainThreadQueries().build()
            } else {
                INSTANCE as CartDatabase
            }
            return INSTANCE!!
        }
    }
}