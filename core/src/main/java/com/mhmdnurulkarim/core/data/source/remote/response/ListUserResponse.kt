package com.mhmdnurulkarim.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
	@field:SerializedName("items")
	val items: List<UserResponse>
)