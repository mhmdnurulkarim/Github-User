package com.mhmdnurulkarim.githubuser.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhmdnurulkarim.githubuser.data.Repository
import com.mhmdnurulkarim.githubuser.di.Injection
import com.mhmdnurulkarim.githubuser.ui.darkTheme.DarkThemeViewModel
import com.mhmdnurulkarim.githubuser.ui.detailUserActivity.DetailUserViewModel
import com.mhmdnurulkarim.githubuser.ui.favoriteActivity.FavoriteViewModel
import com.mhmdnurulkarim.githubuser.ui.followersFragment.FollowersViewModel
import com.mhmdnurulkarim.githubuser.ui.followingFragment.FollowingViewModel
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainViewModel
import com.mhmdnurulkarim.githubuser.ui.splashActivity.SplashViewModel

class ViewModelFactory private constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
            return FollowingViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            return FollowersViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DarkThemeViewModel::class.java)) {
            return DarkThemeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: ViewModelFactory(
                Injection.provideRepository(context)
            )
    }
}