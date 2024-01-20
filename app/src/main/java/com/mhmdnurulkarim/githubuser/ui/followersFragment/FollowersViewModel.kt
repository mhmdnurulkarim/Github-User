package com.mhmdnurulkarim.githubuser.ui.followersFragment

import androidx.lifecycle.ViewModel
import com.mhmdnurulkarim.githubuser.data.Repository

class FollowersViewModel(private val repository: Repository) : ViewModel() {
    fun getUserFollowers(username: String) = repository.getUserFollowers(username)
}