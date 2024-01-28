package com.mhmdnurulkarim.favorite

import androidx.lifecycle.ViewModel

class FavoriteViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    suspend fun getFavoriteList() = githubUserRepository.getFavoriteList()
}