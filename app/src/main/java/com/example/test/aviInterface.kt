package com.example.test

import com.android.volley.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface aviInterface {

    @Multipart
    @POST("/imgup.php?apicall=uploadpic")
    fun uploadUrImg(@Part tags: String, @Part image: MultipartBody.Part)  :Response<aviModel>



    @POST("/imgup.php?apicall=uploadpic")
    fun   uploadPrescriptionImage(@Part tags: String, @Part image: MultipartBody.Part): Response<NetworkResponse>
}