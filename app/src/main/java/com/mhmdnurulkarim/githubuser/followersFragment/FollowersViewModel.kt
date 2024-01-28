package com.mhmdnurulkarim.githubuser.followersFragment

import androidx.lifecycle.ViewModel

class FollowersViewModel(private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository) : ViewModel() {
    fun getUserFollowers(username: String) = githubUserRepository.getUserFollowers(username)
}