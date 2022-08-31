package com.example.test

import com.android.volley.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface aviInterface {

    @Multipart
    @POST("imgFolder/imgup.php?apicall=uploadpic")
    suspend  fun uploadUrImg(@Part("imgName") tags: RequestBody, @Part pic: MultipartBody.Part) :Response<ResponseBody>

}