package com.mhmdnurulkarim.githubuser.splashActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers

class SplashViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun getThemeSetting() = githubUserRepository.getThemeSetting().asLiveData(Dispatchers.IO)
}