package com.mhmdnurulkarim.githubuser.di

import android.content.Context
import com.mhmdnurulkarim.core.data.source.local.room.GithubUserDatabase
import com.mhmdnurulkarim.core.data.source.local.datastore.UserDataStore
import com.mhmdnurulkarim.core.data.source.local.datastore.dataStore

object Injection {
    fun provideRepository(context: Context): com.mhmdnurulkarim.core.data.GithubUserRepository {
        val datastore = UserDataStore.getInstance(context.dataStore)
        val database = GithubUserDatabase.getInstance(context)
        val apiService = ApiConfig.getApiService()
        return com.mhmdnurulkarim.core.data.GithubUserRepository.getInstance(
            dataStore = datastore,
            database = database,
            apiService = apiService
        )
    }
}