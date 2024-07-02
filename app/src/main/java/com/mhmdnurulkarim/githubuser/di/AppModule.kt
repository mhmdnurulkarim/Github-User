package com.mhmdnurulkarim.githubuser.di

import com.mhmdnurulkarim.core.domain.usecase.UserInteractor
import com.mhmdnurulkarim.core.domain.usecase.UserUseCase
import com.mhmdnurulkarim.githubuser.darkTheme.DarkThemeViewModel
import com.mhmdnurulkarim.githubuser.detailUserActivity.DetailUserViewModel
import com.mhmdnurulkarim.githubuser.followersFragment.FollowersViewModel
import com.mhmdnurulkarim.githubuser.followingFragment.FollowingViewModel
import com.mhmdnurulkarim.githubuser.mainActivity.MainViewModel
import com.mhmdnurulkarim.githubuser.splashActivity.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetailUserViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
    viewModel { DarkThemeViewModel(get()) }
}