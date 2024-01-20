package com.mhmdnurulkarim.githubuser.ui.detailUserActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.githubuser.data.Repository
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import kotlinx.coroutines.launch

class DetailUserViewModel(private val repository: Repository) : ViewModel() {
    fun getDetailUser(username: String) = repository.getDetailUser(username)

    fun insertFavoriteUser(user: DetailUserResponse) = viewModelScope.launch {
        repository.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: DetailUserResponse) = viewModelScope.launch {
        repository.deleteFavoriteUser(user)
    }
}