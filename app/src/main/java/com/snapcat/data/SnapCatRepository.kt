package com.snapcat.data

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.snapcat.data.local.database.SnapCatDao
import com.snapcat.data.model.User
import com.snapcat.data.remote.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SnapCatRepository @Inject constructor(
    private val apiService: ApiService,
    private val snapCatDao: SnapCatDao,
) {

    fun register(user: User) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.register(user)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
//            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
//            val errorMessage = errorBody?.message
//            emit(ResultMessage.Error(Exception(errorMessage)))
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun login(user: User) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.login(user)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun forgotPassword(email: String) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.forgotPassword(email)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun history() = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.history()
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun historyById(id: String) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.historyById(id)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun shop() = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.shop()
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun shopById(id: String) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.shopById(id)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

}