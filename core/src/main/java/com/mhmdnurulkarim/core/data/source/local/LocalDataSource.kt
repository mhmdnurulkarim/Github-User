package com.mhmdnurulkarim.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse

suspend fun getFavoriteList(): LiveData<Result<List<GithubUserResponse>>> = liveData {
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

suspend fun insertFavoriteUser(user: GithubUserResponse) = dao.insertFavoriteUser(user)

suspend fun deleteFavoriteUser(user: GithubUserResponse) = dao.deleteFavoriteUser(user)