package com.mhmdnurulkarim.githubuser.splashActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers

class SplashViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getThemeSetting() = userUseCase.getThemeSetting().asLiveData(Dispatchers.IO)
}