package com.mhmdnurulkarim.githubuser.mainActivity

import androidx.lifecycle.ViewModel

class MainViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun searchUser(query: String) = githubUserRepository.searchUser(query)
}