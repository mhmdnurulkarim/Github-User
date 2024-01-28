package com.mhmdnurulkarim.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class GithubUserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "username")
    val login: String? = "",

    @ColumnInfo(name = "name")
    val name: String? = "",

    @ColumnInfo(name = "following")
    val following: Int? = 0,

    @ColumnInfo(name = "follower")
    val followers: Int? = 0,

    @ColumnInfo(name = "repository")
    val publicRepos: Int? = 0,

    @ColumnInfo(name = "avatar")
    val avatarUrl: String? = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
) : Parcelable