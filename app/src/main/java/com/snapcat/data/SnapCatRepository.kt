package com.snapcat.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.snapcat.data.local.database.SnapCatDao
import com.snapcat.data.model.User
import com.snapcat.data.remote.response.ResponseGetAllHistories
import com.snapcat.data.remote.response.ResponseGetAllShop
import com.snapcat.data.remote.response.ResponseGetUser
import com.snapcat.data.remote.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SnapCatRepository (
    private val apiService: ApiService,
) {

    fun register(user: User) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.register(user)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(Exception(errorBody ?: "Unknown error")))
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
            val errorBody = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(Exception(errorBody ?: "Unknown error")))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun forgotPassword(email: User) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.forgotPassword(email)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(Exception(errorBody ?: "Unknown error")))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun getAllShop(token: String, id: String) = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.getAllShop("Bearer $token", id)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun getAllHistories(token: String, id: String): LiveData<ResultMessage<ResponseGetAllHistories>> = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.getAllHistories("Bearer $token", id)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

    fun getUser(token: String, id: String): LiveData<ResultMessage<ResponseGetUser>> = liveData {
        try {
            emit(ResultMessage.Loading)
            val response = apiService.getUser(token, id)
            emit(ResultMessage.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            emit(ResultMessage.Error(e))
        } catch (e: IOException) {
            emit(ResultMessage.Error(Exception("No network connection")))
        }
    }

//    fun historyById(id: String) = liveData {
//        try {
//            emit(ResultMessage.Loading)
//            val response = apiService.historyById(id)
//            emit(ResultMessage.Success(response))
//        } catch (e: HttpException) {
//            val jsonInString = e.response()?.errorBody()?.string()
//            emit(ResultMessage.Error(e))
//        } catch (e: IOException) {
//            emit(ResultMessage.Error(Exception("No network connection")))
//        }
//    }
//
//
//    fun shopById(id: String) = liveData {
//        try {
//            emit(ResultMessage.Loading)
//            val response = apiService.shopById(id)
//            emit(ResultMessage.Success(response))
//        } catch (e: HttpException) {
//            val jsonInString = e.response()?.errorBody()?.string()
//            emit(ResultMessage.Error(e))
//        } catch (e: IOException) {
//            emit(ResultMessage.Error(Exception("No network connection")))
//        }
//    }

    companion object {
        @Volatile
        private var instance: SnapCatRepository? = null
        fun getInstance(apiService: ApiService): SnapCatRepository =
            instance ?: synchronized(this) {
                instance ?: SnapCatRepository(apiService)
            }.also { instance = it }
    }

}