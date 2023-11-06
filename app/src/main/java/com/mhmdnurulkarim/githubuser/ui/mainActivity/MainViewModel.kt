package com.mhmdnurulkarim.githubuser.ui.mainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun searchUser(query: String) = repository.searchUser(query)
}