package com.mhmdnurulkarim.githubuser.data.network

import com.mhmdnurulkarim.githubuser.utils.Const.BASE_URL
import com.mhmdnurulkarim.githubuser.utils.Const.GITHUB_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    fun getApiService(): ApiService {
        val auth = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", GITHUB_TOKEN)
                .build()
            chain.proceed(requestHeaders)
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(auth)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}