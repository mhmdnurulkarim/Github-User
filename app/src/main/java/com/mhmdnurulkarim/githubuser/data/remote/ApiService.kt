package com.mhmdnurulkarim.githubuser.data.remote

import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser(
        @Query("q")
        query: String
    ): Call<SearchResponse>

    @GET("users/{login}")
    fun getDetailUser(
        @Path("login")
        login: String
    ): Call<DetailUserResponse>

    @GET("users/{login}/followers")
    fun getUserFollowers(
        @Path("login")
        login: String
    ): Call<List<DetailUserResponse>>

    @GET("users/{login}/following")
    fun getUserFollowing(
        @Path("login")
        login: String
    ): Call<List<DetailUserResponse>>
}