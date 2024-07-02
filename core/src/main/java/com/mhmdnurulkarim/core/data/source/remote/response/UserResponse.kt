package com.mhmdnurulkarim.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("login")
    val login: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("following")
    val following: Int?,

    @field:SerializedName("followers")
    val followers: Int?,

    @field:SerializedName("public_repos")
    val publicRepos: Int?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
)