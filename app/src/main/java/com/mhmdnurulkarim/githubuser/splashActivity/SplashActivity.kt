package com.mhmdnurulkarim.githubuser.splashActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.databinding.ActivitySplashBinding
import com.mhmdnurulkarim.githubuser.ViewModelFactory
import com.mhmdnurulkarim.githubuser.mainActivity.MainActivity
import com.mhmdnurulkarim.core.utils.Const.TIME_SPLASH

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this@SplashActivity)
            .load(R.mipmap.ic_github_launcher)
            .into(binding.ivPictureSplash)

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