package com.mhmdnurulkarim.githubuser.data.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("items")
    val items: List<DetailUserResponse>
)

@Entity(tableName = "user")
data class DetailUserResponse(
    @field:SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int? = 0,

    @field:SerializedName("login")
    @ColumnInfo(name = "username")
    val login: String? = "",

    @field:SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String? = "",

    @field:SerializedName("following")
    @ColumnInfo(name = "following")
    val following: Int? = 0,

    @field:SerializedName("followers")
    @ColumnInfo(name = "follower")
    val followers: Int? = 0,

    @field:SerializedName("public_repos")
    @ColumnInfo(name = "repository")
    val publicRepos: Int? = 0,

    @field:SerializedName("avatar_url")
    @ColumnInfo(name = "avatar")
    val avatarUrl: String? = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)