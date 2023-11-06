package com.mhmdnurulkarim.githubuser.ui.followersFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository

class FollowersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getUserFollowers(username: String) = repository.getUserFollowers(username)
}