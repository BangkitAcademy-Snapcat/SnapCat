package com.snapcat.data.remote.retrofit

import com.snapcat.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val requestUrl = req.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val requestHeaders = req.newBuilder()
            .url(requestUrl)
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(requestHeaders)
    }
}