package com.mhmdnurulkarim.githubuser.ui.splashActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.githubuser.data.Repository
import kotlinx.coroutines.Dispatchers

class SplashViewModel(private val repository: Repository): ViewModel() {
    fun getThemeSetting() = repository.getThemeSetting().asLiveData(Dispatchers.IO)
}