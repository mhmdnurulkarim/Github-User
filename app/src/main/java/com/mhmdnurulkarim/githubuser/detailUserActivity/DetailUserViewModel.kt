package com.mhmdnurulkarim.githubuser.detailUserActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import kotlinx.coroutines.launch

class DetailUserViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun getDetailUser(username: String) = githubUserRepository.getDetailUser(username)

    fun insertFavoriteUser(user: GithubUserResponse) = viewModelScope.launch {
        githubUserRepository.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: GithubUserResponse) = viewModelScope.launch {
        githubUserRepository.deleteFavoriteUser(user)
    }
}