package com.mhmdnurulkarim.githubuser.ui.splashActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.databinding.ActivitySplashBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.Const.TIME_SPLASH

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPictureSplash.load(R.mipmap.ic_github_launcher) {
            transformations(CircleCropTransformation())
        }

        Handler(mainLooper).postDelayed({
            splashViewModel.getThemeSetting().observe(this@SplashActivity) { isDarkMode ->
                if (isDarkMode) {
                    move()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    move()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }, TIME_SPLASH)
    }

    private fun move() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}