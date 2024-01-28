package com.mhmdnurulkarim.core.data.source.remote.network

import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.core.data.source.remote.response.SearchGithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getAllUser(): List<GithubUserResponse>

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String
    ): SearchGithubUserResponse

    @GET("users/{login}")
    suspend fun getDetailUser(
        @Path("login") login: String
    ): GithubUserResponse

    @GET("users/{login}/followers")
    suspend fun getUserFollowers(
        @Path("login") login: String
    ): List<GithubUserResponse>

    @GET("users/{login}/following")
    suspend fun getUserFollowing(
        @Path("login") login: String
    ): List<GithubUserResponse>
}