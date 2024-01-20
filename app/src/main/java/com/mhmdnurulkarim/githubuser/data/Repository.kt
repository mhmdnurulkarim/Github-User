package com.mhmdnurulkarim.githubuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mhmdnurulkarim.githubuser.data.database.UserDao
import com.mhmdnurulkarim.githubuser.data.database.UserDatabase
import com.mhmdnurulkarim.githubuser.data.datastore.UserDataStore
import com.mhmdnurulkarim.githubuser.data.network.ApiService
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.network.SearchResponse

class Repository private constructor(
    private val dataStore: UserDataStore,
    database: UserDatabase,
    private val apiService: ApiService
) {
    private val dao: UserDao = database.userDao()

    fun searchUser(query: String): LiveData<Result<SearchResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.searchUser(query)
            emit(Result.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.toString()))
        }
    }

    fun getDetailUser(username: String): LiveData<Result<DetailUserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(username)
            emit(Result.Success(response))
            if (dao.getFavoriteDetailUser(username) != null) {
                emit(Result.Success(dao.getFavoriteDetailUser(username)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.toString()))
        }
    }

    fun getUserFollowing(username: String): LiveData<Result<List<DetailUserResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUserFollowing(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.toString()))
        }
    }

    fun getUserFollowers(username: String): LiveData<Result<List<DetailUserResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUserFollowers(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.toString()))
        }
    }

    suspend fun getFavoriteList(): LiveData<Result<List<DetailUserResponse>>> = liveData {
        emit(Result.Loading)
        if (dao.getFavoriteListUser().isEmpty()) {
            emit(Result.Error("Null"))
        } else {
            try {
                emit(Result.Success(dao.getFavoriteListUser()))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.toString()))
            }
        }
    }

    suspend fun insertFavoriteUser(user: DetailUserResponse) = dao.insertFavoriteUser(user)

    suspend fun deleteFavoriteUser(user: DetailUserResponse) = dao.deleteFavoriteUser(user)

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        dataStore.saveThemeSetting(isDarkModeActive)

    fun getThemeSetting() = dataStore.getThemeSetting()

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            dataStore: UserDataStore,
            database: UserDatabase,
            apiService: ApiService
        ): Repository = instance ?: Repository(dataStore, database, apiService)
    }
}