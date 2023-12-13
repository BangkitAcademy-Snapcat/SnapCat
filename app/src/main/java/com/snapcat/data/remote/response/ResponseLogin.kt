package com.snapcat.data.remote.response

import com.google.gson.annotations.SerializedName
import com.snapcat.data.model.User

data class ResponseLogin(

	@field:SerializedName("data")
	val data: DataLogin? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataLogin(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
