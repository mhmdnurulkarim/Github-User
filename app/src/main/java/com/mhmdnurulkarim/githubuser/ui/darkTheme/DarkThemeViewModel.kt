package com.mhmdnurulkarim.githubuser.ui.darkTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.githubuser.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DarkThemeViewModel(private val repository: Repository): ViewModel() {
    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        repository.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = repository.getThemeSetting().asLiveData(Dispatchers.IO)
}