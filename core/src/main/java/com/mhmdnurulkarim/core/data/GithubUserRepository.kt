package com.mhmdnurulkarim.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mhmdnurulkarim.core.data.source.local.room.GithubUserDao
import com.mhmdnurulkarim.core.data.source.local.room.GithubUserDatabase
import com.mhmdnurulkarim.core.data.source.local.datastore.UserDataStore
import com.mhmdnurulkarim.core.data.source.remote.network.ApiService
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.core.data.source.remote.response.SearchGithubUserResponse

class GithubUserRepository private constructor(
    private val dataStore: UserDataStore,
    database: GithubUserDatabase,
    private val apiService: ApiService
) {
    private val dao: GithubUserDao = database.githubUserDao()

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        dataStore.saveThemeSetting(isDarkModeActive)

    fun getThemeSetting() = dataStore.getThemeSetting()

    companion object {
        @Volatile
        private var instance: GithubUserRepository? = null

        fun getInstance(
            dataStore: UserDataStore,
            database: GithubUserDatabase,
            apiService: ApiService
        ): GithubUserRepository = instance ?: GithubUserRepository(dataStore, database, apiService)
    }
}