package com.mhmdnurulkarim.githubuser.followersFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase

class FollowersViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getUserFollowers(username: String) = userUseCase.getUserFollowers(username).asLiveData()
}