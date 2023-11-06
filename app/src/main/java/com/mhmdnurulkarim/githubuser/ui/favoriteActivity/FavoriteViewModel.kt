package com.mhmdnurulkarim.githubuser.ui.favoriteActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    suspend fun getFavoriteList() = repository.getFavoriteList()
}