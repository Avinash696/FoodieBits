package com.example.zepto.db

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

//    private const val BASE_URL = "http://localhost/t/ecomm/"
    private const val BASE_URL = "http://localhost/imgFolder/"

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}