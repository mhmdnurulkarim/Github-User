package com.mhmdnurulkarim.githubuser.ui.followingFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mhmdnurulkarim.githubuser.data.dataStore.Repository

class FollowingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getUserFollowing(username: String) = repository.getUserFollowing(username)
}