package com.mhmdnurulkarim.githubuser.ui.darkTheme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DarkThemeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        repository.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = repository.getThemeSetting().asLiveData(Dispatchers.IO)
}