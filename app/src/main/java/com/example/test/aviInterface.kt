package com.example.test

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface aviInterface {

    @Multipart
    @POST("imgFolder/imgup.php?apicall=uploadpic")
    suspend  fun uploadUrImg(@Part("tags") tags: RequestBody, @Part pic: MultipartBody.Part) :Response<ImageUpload>

}

data class ImageUpload(val error: Boolean?, val message: String?)