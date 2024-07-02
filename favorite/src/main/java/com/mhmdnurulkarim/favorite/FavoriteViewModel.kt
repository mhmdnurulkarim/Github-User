package com.mhmdnurulkarim.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase

class FavoriteViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getFavoriteList() = userUseCase.getFavoriteListUser().asLiveData()
}