package com.mhmdnurulkarim.githubuser.followingFragment

import androidx.lifecycle.ViewModel

class FollowingViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun getUserFollowing(username: String) = githubUserRepository.getUserFollowing(username)
}