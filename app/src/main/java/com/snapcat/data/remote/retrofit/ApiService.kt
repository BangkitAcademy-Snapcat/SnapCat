package com.snapcat.data.remote.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("data/snapcat")
    suspend fun snapCat()
}