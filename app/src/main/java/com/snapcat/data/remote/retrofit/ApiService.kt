package com.snapcat.data.remote.retrofit

import com.snapcat.data.model.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("data/snapcat")
    suspend fun snapCat()

    @POST("api/users/register")
    suspend fun register(@Body requestBody: User): Response<ResponseBody>

    @POST("api/users/login")
    suspend fun login(@Body requestBody: User): Response<ResponseBody>

    @POST("api/users/forgot-password/{email}")
    suspend fun forgotPassword(@Path("email") email: String): Response<ResponseBody>

    @GET("api/users/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<ResponseBody>

//    @POST("api/users/logout")
//    suspend fun logout(@Body requestBody: User): Response<ResponseBody>

    @GET("api/shop")
    suspend fun shop(): Response<ResponseBody>

    @GET("api/shop/{id}")
    suspend fun shopById(@Path("id") id: String): Response<ResponseBody>

    @GET("api/history")
    suspend fun history(): Response<ResponseBody>

    @GET("api/history/{id}")
    suspend fun historyById(@Path("id") id: String): Response<ResponseBody>



}