package com.mhmdnurulkarim.githubuser.data.dataStore

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.SearchResponse
import com.mhmdnurulkarim.githubuser.data.local.UserDao
import com.mhmdnurulkarim.githubuser.data.local.UserDatabase
import com.mhmdnurulkarim.githubuser.data.remote.ApiConfig
import com.mhmdnurulkarim.githubuser.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {

    private val retrofit: ApiService = ApiConfig.create()
    private val dao: UserDao
    private val dataStore: UserDataStore

    init {
        val database: UserDatabase = UserDatabase.getInstance(application)
        dao = database.userDao()
        dataStore = UserDataStore.getInstance(application)
    }

    fun searchUser(query: String): LiveData<Resource<List<DetailUserResponse>>> {
        val listUser = MutableLiveData<Resource<List<DetailUserResponse>>>()

        listUser.postValue(Resource.Loading())
        retrofit.searchUser(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                val list = response.body()?.items
                if (response.isSuccessful){
                    listUser.postValue(Resource.Success(list))
                }else {
                    listUser.postValue(Resource.Error(null))
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }
        })
        return listUser
    }

    suspend fun getDetailUser(username: String): LiveData<Resource<DetailUserResponse>> {

        val user = MutableLiveData<Resource<DetailUserResponse>>()

        if (dao.getFavoriteDetailUser(username) != null) {
            user.postValue(Resource.Success(dao.getFavoriteDetailUser(username)))
        } else {
            retrofit.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                    val result = response.body()
                    user.postValue(Resource.Success(result))
                }
                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {}
            })
        }

        return user
    }

    fun getUserFollowing(username: String): LiveData<Resource<List<DetailUserResponse>>> {

        val listUser = MutableLiveData<Resource<List<DetailUserResponse>>>()

        listUser.postValue(Resource.Loading())
        retrofit.getUserFollowing(username).enqueue(object : Callback<List<DetailUserResponse>> {
            override fun onResponse(call: Call<List<DetailUserResponse>>, response: Response<List<DetailUserResponse>>) {
                val list = response.body()
                if (response.isSuccessful){
                    listUser.postValue(Resource.Success(list))
                } else {
                    listUser.postValue(Resource.Error(null))
                }
            }

            override fun onFailure(call: Call<List<DetailUserResponse>>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }
        })
        return listUser
    }

    fun getUserFollowers(username: String): LiveData<Resource<List<DetailUserResponse>>> {

        val listUser = MutableLiveData<Resource<List<DetailUserResponse>>>()

        listUser.postValue(Resource.Loading())
        retrofit.getUserFollowers(username).enqueue(object : Callback<List<DetailUserResponse>> {
            override fun onResponse(call: Call<List<DetailUserResponse>>, response: Response<List<DetailUserResponse>>) {
                val list = response.body()
                if (response.isSuccessful) {
                    listUser.postValue(Resource.Success(list))
                } else {
                    listUser.postValue(Resource.Error(null))
                }
            }

            override fun onFailure(call: Call<List<DetailUserResponse>>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }
        })

        return listUser
    }

    suspend fun getFavoriteList(): LiveData<Resource<List<DetailUserResponse>>> {

        val listFavorite = MutableLiveData<Resource<List<DetailUserResponse>>>()
        listFavorite.postValue(Resource.Loading())

        if (dao.getFavoriteListUser().isEmpty())
            listFavorite.postValue(Resource.Error(null))
        else
            listFavorite.postValue(Resource.Success(dao.getFavoriteListUser()))

        return listFavorite
    }

    suspend fun insertFavoriteUser(user: DetailUserResponse) = dao.insertFavoriteUser(user)

    suspend fun deleteFavoriteUser(user: DetailUserResponse) = dao.deleteFavoriteUser(user)

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) = dataStore.saveThemeSetting(isDarkModeActive)

    fun getThemeSetting() = dataStore.getThemeSetting()
}