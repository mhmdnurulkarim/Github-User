package com.mhmdnurulkarim.githubuser.ui.detailUserActivity

import android.app.Application
import androidx.lifecycle.*
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository
import kotlinx.coroutines.launch

class DetailUserViewModel(application: Application): AndroidViewModel(application) {
    val repository = Repository(application)

    suspend fun getDetailUser(username: String) = repository.getDetailUser(username)

    fun insertFavoriteUser(user: DetailUserResponse) = viewModelScope.launch {
        repository.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: DetailUserResponse) = viewModelScope.launch {
        repository.deleteFavoriteUser(user)
    }
}