package com.mhmdnurulkarim.githubuser.ui.splashActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getThemeSetting() = repository.getThemeSetting().asLiveData(Dispatchers.IO)
}