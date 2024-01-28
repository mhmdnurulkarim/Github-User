package com.mhmdnurulkarim.githubuser

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhmdnurulkarim.githubuser.di.Injection
import com.mhmdnurulkarim.githubuser.darkTheme.DarkThemeViewModel
import com.mhmdnurulkarim.githubuser.detailUserActivity.DetailUserViewModel
import com.mhmdnurulkarim.favorite.FavoriteViewModel
import com.mhmdnurulkarim.githubuser.followersFragment.FollowersViewModel
import com.mhmdnurulkarim.githubuser.followingFragment.FollowingViewModel
import com.mhmdnurulkarim.githubuser.mainActivity.MainViewModel
import com.mhmdnurulkarim.githubuser.splashActivity.SplashViewModel

class ViewModelFactory private constructor(
    private val githubUserRepository: com.mhmdnurulkarim.core.data.GithubUserRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
            return FollowingViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            return FollowersViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(com.mhmdnurulkarim.favorite.FavoriteViewModel::class.java)) {
            return com.mhmdnurulkarim.favorite.FavoriteViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(githubUserRepository) as T
        } else if (modelClass.isAssignableFrom(DarkThemeViewModel::class.java)) {
            return DarkThemeViewModel(githubUserRepository) as T
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