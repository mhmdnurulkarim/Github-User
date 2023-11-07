package com.mhmdnurulkarim.githubuser.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q")
        query: String
    ): SearchResponse

    @GET("users/{login}")
    suspend fun getDetailUser(
        @Path("login")
        login: String
    ): DetailUserResponse

    @GET("users/{login}/followers")
    suspend fun getUserFollowers(
        @Path("login")
        login: String
    ): List<DetailUserResponse>

    @GET("users/{login}/following")
    suspend fun getUserFollowing(
        @Path("login")
        login: String
    ): List<DetailUserResponse>
}