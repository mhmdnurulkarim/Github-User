package com.mhmdnurulkarim.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("login")
    val login: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("following")
    val following: Int? = 0,

    @field:SerializedName("followers")
    val followers: Int? = 0,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = 0,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",
)