package com.mhmdnurulkarim.githubuser.darkTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DarkThemeViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        userUseCase.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = userUseCase.getThemeSetting().asLiveData(Dispatchers.IO)
}