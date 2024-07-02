package com.mhmdnurulkarim.githubuser.detailUserActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.core.data.source.remote.response.UserResponse
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class DetailUserViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getDetailUser(username: String) = userUseCase.getDetailUser(username).asLiveData()

    fun getFavoriteDetailUser(username: String) = userUseCase.getFavoriteDetailUser(username).asLiveData()

    fun insertFavoriteUser(user: User) = viewModelScope.launch {
        userUseCase.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: User) = viewModelScope.launch {
        userUseCase.deleteFavoriteUser(user)
    }
}