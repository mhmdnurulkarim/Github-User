package com.mhmdnurulkarim.githubuser.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase

class MainViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun searchUser(query: String?) = userUseCase.searchUser(query).asLiveData()
}