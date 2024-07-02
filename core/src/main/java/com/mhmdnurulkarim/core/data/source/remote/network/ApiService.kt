package com.mhmdnurulkarim.core.data.source.remote.network

import com.mhmdnurulkarim.core.data.source.remote.response.ListUserResponse
import com.mhmdnurulkarim.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String?
    ): ListUserResponse

    @GET("users/{login}")
    suspend fun getDetailUser(
        @Path("login") login: String?
    ): UserResponse

    @GET("users/{login}/followers")
    suspend fun getUserFollowers(
        @Path("login") login: String?
    ): List<UserResponse>

    @GET("users/{login}/following")
    suspend fun getUserFollowing(
        @Path("login") login: String?
    ): List<UserResponse>
}