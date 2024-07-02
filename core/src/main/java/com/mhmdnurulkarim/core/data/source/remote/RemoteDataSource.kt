package com.mhmdnurulkarim.core.data.source.remote

import android.util.Log
import com.mhmdnurulkarim.core.data.source.remote.network.ApiResponse
import com.mhmdnurulkarim.core.data.source.remote.network.ApiService
import com.mhmdnurulkarim.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun searchUser(query: String?): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userSearch = apiService.searchUser(query)
                val userArray = userSearch.items
                if (userArray.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(userArray))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailUser(username: String): Flow<ApiResponse<UserResponse>> =
        flow {
            try {
                val userDetail = apiService.getDetailUser(username)
                emit(ApiResponse.Success(userDetail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserFollowers(username: String): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userFollower = apiService.getUserFollowers(username)
                emit(ApiResponse.Success(userFollower))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserFollowing(username: String): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userFollowing = apiService.getUserFollowing(username)
                emit(ApiResponse.Success(userFollowing))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
}