package com.mhmdnurulkarim.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.core.data.source.remote.response.SearchGithubUserResponse

fun searchUser(query: String): LiveData<Result<SearchGithubUserResponse>> = liveData {
    emit(Result.Loading)
    try {
        val response = apiService.searchUser(query)
        emit(Result.Success(response))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(Result.Error(e.toString()))
    }
}

fun getDetailUser(username: String): LiveData<Result<GithubUserResponse>> = liveData {
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

fun getUserFollowing(username: String): LiveData<Result<List<GithubUserResponse>>> = liveData {
    emit(Result.Loading)
    try {
        val response = apiService.getUserFollowing(username)
        emit(Result.Success(response))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(Result.Error(e.toString()))
    }
}

fun getUserFollowers(username: String): LiveData<Result<List<GithubUserResponse>>> = liveData {
    emit(Result.Loading)
    try {
        val response = apiService.getUserFollowers(username)
        emit(Result.Success(response))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(Result.Error(e.toString()))
    }
}