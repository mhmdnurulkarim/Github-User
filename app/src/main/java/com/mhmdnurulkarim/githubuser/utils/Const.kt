package com.mhmdnurulkarim.githubuser.utils

import androidx.annotation.StringRes
import com.mhmdnurulkarim.githubuser.BuildConfig
import com.mhmdnurulkarim.githubuser.R

object Const {
    @StringRes
    val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)
    const val GITHUB_TOKEN = BuildConfig.API_KEY
    const val BASE_URL = BuildConfig.BASE_URL

    const val TIME_SPLASH = 2000L
    const val EXTRA_USER = "EXTRA_USER"

    const val DATA_STORE_NAME = "USER_DATASTORE"
}