package com.mhmdnurulkarim.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int?,
    val login: String?,
    val name: String?,
    val following: Int?,
    val followers: Int?,
    val publicRepos: Int?,
    val avatarUrl: String?,
    var isFavorite: Boolean?
) : Parcelable