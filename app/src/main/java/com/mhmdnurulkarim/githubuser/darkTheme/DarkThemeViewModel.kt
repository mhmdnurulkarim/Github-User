package com.mhmdnurulkarim.githubuser.darkTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DarkThemeViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        githubUserRepository.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = githubUserRepository.getThemeSetting().asLiveData(Dispatchers.IO)
}