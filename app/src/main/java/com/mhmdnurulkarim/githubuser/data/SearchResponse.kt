package com.mhmdnurulkarim.githubuser.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("items")
	val items: List<DetailUserResponse>
)