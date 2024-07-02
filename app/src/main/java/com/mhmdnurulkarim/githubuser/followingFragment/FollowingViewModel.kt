package com.mhmdnurulkarim.githubuser.followingFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase

class FollowingViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getUserFollowing(username: String) = userUseCase.getUserFollowing(username).asLiveData()
}