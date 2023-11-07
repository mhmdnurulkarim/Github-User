package com.mhmdnurulkarim.githubuser.di

import android.content.Context
import com.mhmdnurulkarim.githubuser.data.Repository
import com.mhmdnurulkarim.githubuser.data.database.UserDatabase
import com.mhmdnurulkarim.githubuser.data.datastore.UserDataStore
import com.mhmdnurulkarim.githubuser.data.datastore.dataStore
import com.mhmdnurulkarim.githubuser.data.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val datastore = UserDataStore.getInstance(context.dataStore)
        val database = UserDatabase.getInstance(context)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(dataStore = datastore, database = database, apiService = apiService)
    }
}