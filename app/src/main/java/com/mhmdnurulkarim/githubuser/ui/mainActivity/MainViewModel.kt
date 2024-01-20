package com.mhmdnurulkarim.githubuser.ui.mainActivity

import androidx.lifecycle.ViewModel
import com.mhmdnurulkarim.githubuser.data.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun searchUser(query: String) = repository.searchUser(query)
}