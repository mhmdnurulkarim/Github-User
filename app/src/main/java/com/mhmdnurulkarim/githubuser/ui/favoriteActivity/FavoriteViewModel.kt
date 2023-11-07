package com.mhmdnurulkarim.githubuser.ui.favoriteActivity

import androidx.lifecycle.ViewModel
import com.mhmdnurulkarim.githubuser.data.Repository

class FavoriteViewModel(private val repository: Repository): ViewModel() {
    suspend fun getFavoriteList() = repository.getFavoriteList()
}