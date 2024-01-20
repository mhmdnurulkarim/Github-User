package com.mhmdnurulkarim.githubuser.ui.followingFragment

import androidx.lifecycle.ViewModel
import com.mhmdnurulkarim.githubuser.data.Repository

class FollowingViewModel(private val repository: Repository) : ViewModel() {
    fun getUserFollowing(username: String) = repository.getUserFollowing(username)
}