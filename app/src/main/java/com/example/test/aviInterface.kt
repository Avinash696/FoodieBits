package com.example.test

import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface aviInterface {

    @Multipart
    @POST("imgFolder/imgup.php?apicall=uploadpic")
    suspend  fun uploadUrImg(@Part("tags") tags: RequestBody, @Part pic: MultipartBody.Part) :Response<ImageUpload>

    //retailer
    //add user
    @GET("imgFolder/showAdmin.php")
   suspend fun getRetailerAddUser() :Response<retailerResponceAddUserModel>

}

data class ImageUpload(val error: Boolean?, val message: String?)