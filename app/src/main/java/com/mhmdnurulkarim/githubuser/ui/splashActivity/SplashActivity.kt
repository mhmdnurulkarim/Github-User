package com.mhmdnurulkarim.githubuser.ui.splashActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.data.dataStore.Resource
import com.mhmdnurulkarim.githubuser.databinding.ActivitySplashBinding
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.Const.TIME_SPLASH

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this@SplashActivity)
            .load(R.mipmap.ic_github_launcher_round)
            .into(binding.ivPictureSplash)

        Handler(mainLooper).postDelayed({
            splashViewModel.getThemeSetting().observe(this@SplashActivity){ isDarkMode ->
                if (isDarkMode){
                    move()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    move()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        },TIME_SPLASH)
    }

    private fun move(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}