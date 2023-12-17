package com.snapcat.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGetAllHistories(

	@field:SerializedName("data")
	val dataItem: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("catId")
	val catId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String
)
