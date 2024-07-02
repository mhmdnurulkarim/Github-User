package com.mhmdnurulkarim.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "username")
    val login: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "following")
    val following: Int?,

    @ColumnInfo(name = "followers")
    val followers: Int?,

    @ColumnInfo(name = "repository")
    val publicRepos: Int?,

    @ColumnInfo(name = "avatar")
    val avatarUrl: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?
) : Parcelable