package com.example.test

import com.example.zepto.model.*
import com.example.zepto.model.apiResponceModel.retailerResponceAddUserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface aviInterface {

    @Multipart
    @POST("imgFolder/imgup.php?apicall=uploadpic")
    suspend fun uploadUrImg(
        @Part("tags") tags: RequestBody,
        @Part pic: MultipartBody.Part
    ): Response<ImageUpload>

    //retailer
    //add user
    @GET("imgFolder/showAdmin.php")
    suspend fun getRetailerAddUser(): Response<retailerResponceAddUserModel>

    @Multipart
    @POST("imgFolder/addUserAdmins.php?addAdmin=addAdminPost")
    suspend fun createRetailer(
        @Part("id") id: RequestBody,
        @Part("role") role: RequestBody,
        @Part("myName") myName: RequestBody,
        @Part("password") password: RequestBody,
        @Part("email") email: RequestBody,
        @Part("mobileNo") mobileNo: RequestBody,
        @Part("address") address: RequestBody,
        @Part("adhar") adhar: RequestBody,
        @Part("panCard") panCard: RequestBody,
        @Part("shopReqNo") shopReqNo: RequestBody,
        @Part imgFile: MultipartBody.Part,
        @Part("whoCreated") whoCreated: RequestBody
    ): Response<ImageUpload>


    //admin Login
    @GET("imgFolder/showAdmin.php")
    suspend fun authenticateAdmin(): Response<retailerResponceAddUserModel>

    //MainCategory
    @GET("imgFolder/mainCategory.php?mainCategoryAdd=getMainCategory")
    suspend fun getMainCategory(): Response<mainCategoryModel>

    @Multipart
    @POST("imgFolder/mainCategory.php?mainCategoryAdd=postMainCategory")
    suspend fun postMainCategory(
        @Part("id") id: RequestBody,
        @Part categoryImg: MultipartBody.Part,
        @Part("categoryName") categoryName: RequestBody,
        @Part("categoryStatus") categoryStatus: RequestBody
    ): Response<mainCategoryPostModel>

    //MainSubCategory
    @GET("imgFolder/mainSubCategory.php?mainSubCategoryAdd=getMainSubCategory")
    suspend fun getMainSubCategory(): Response<mainSubCategoryModel>

    @Multipart
    @POST("imgFolder/mainSubCategory.php?mainSubCategoryAdd=postMainSubCategory")
    suspend fun postMainSubCategory(
        @Part("categoryId") categoryId: RequestBody, @Part subCategoryImg: MultipartBody.Part,
        @Part("subCategoryName") subCategoryName: RequestBody,
        @Part("subCategoryStatus") subCategoryStatus: RequestBody
    ): Response<mainCategoryPostModel>


    //Sub Product
    @GET("imgFolder/mainSubProduct.php?mainSubProductAdd=getMainSubProduct")
    suspend fun getMainSubProduct(): Response<mainSubProductResponceModel>

    @Multipart
    @POST("imgFolder/mainSubCategory.php?mainSubCategoryAdd=postMainSubCategory")
    suspend fun postMainSubProduct(
        @Part("mainSubCategoryId") mainSubCategoryId: RequestBody,
        @Part subProductImg: MultipartBody.Part,
        @Part("subProductName") subProductName: RequestBody,
        @Part("subProductStatus") subProductStatus: RequestBody,
        @Part("addProductQuantity") addProductQuantity: RequestBody,
        @Part("addProductPrice") addProductPrice: RequestBody,
        @Part("addProductDescription") addProductDescription: RequestBody
    ): Response<mainCategoryPostModel>

    //trending
    @GET("imgFolder/mainTrending.php?mainTrending=getMainTrending")
    suspend fun getMainTrending(): Response<trendingResponceModel>

    @Multipart
    @POST("imgFolder/mainTrending.php?mainTrending=postMainTrending")
    suspend fun postMainTrending(
        @Part("idUser") idUser: RequestBody,
        @Part productImg: MultipartBody.Part,
        @Part("productName") productName: RequestBody,
        @Part("productQty") productQty: RequestBody,
        @Part("discountedPrice") discountedPrice: RequestBody,
        @Part("priceShow") priceShow: RequestBody
    ): Response<mainCategoryPostModel>

    //document delivery upload
    @Multipart
    @POST("imgFolder/deliveryDocument.php?deliveryDoc=postDeliveryDoc")
    suspend fun postDeliveryDocument(
        @Part("vechileType") vechileType: RequestBody,
        @Part("vechileNo") vechileNo: RequestBody,
        @Part("vechileLincense") vechileLincense: RequestBody,
        @Part("userName") userName: RequestBody,
        @Part("userEmail") userEmail: RequestBody,
        @Part("panCard") panCard: RequestBody,
        @Part("adharCard") adharCard: RequestBody,
        @Part("emergencyNo") emergencyNo: RequestBody,
        @Part("emergencyName") emergencyName: RequestBody,
        @Part("bankName") bankName: RequestBody,
        @Part("accountNo") accountNo: RequestBody,
        @Part("ifscCode") ifscCode: RequestBody,
        @Part("branchName") branchName: RequestBody,
        @Part fileAdhar: MultipartBody.Part,
        @Part filePan: MultipartBody.Part,
    ): Response<mainCategoryPostModel>

    //order placed
    @Multipart
    @POST("imgFolder/orderPlaced.php?orderPlaced=postOrderPlaced")
    suspend fun postOrderedPlaced(
        @Part("item") item: ResponseBody,
        @Part("type") type: ResponseBody
    ): Response<mainCategoryPostModel>

    @GET("imgFolder/orderPlaced.php?orderPlaced=getOrderPlaced")
    suspend fun getPlacedOrder():Response<orderPlacedResponceModel>
}

data class ImageUpload(val error: Boolean?, val message: String?)