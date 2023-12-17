package com.snapcat.di

import android.content.Context
import com.snapcat.data.SnapCatRepository
import com.snapcat.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): SnapCatRepository {
        //val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return SnapCatRepository.getInstance(apiService)
    }
}