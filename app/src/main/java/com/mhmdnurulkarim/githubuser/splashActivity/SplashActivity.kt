package com.mhmdnurulkarim.githubuser.splashActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.databinding.ActivitySplashBinding
import com.mhmdnurulkarim.githubuser.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.Const.TIME_SPLASH
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this@SplashActivity)
            .load(R.mipmap.ic_github_launcher)
            .into(binding.ivPictureSplash)

        Handler(mainLooper).postDelayed({
            move()
//            splashViewModel.getThemeSetting().observe(this@SplashActivity) { isDarkMode ->
//                if (isDarkMode) {
//                    move()
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                } else {
//                    move()
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                }
//            }
        }, TIME_SPLASH)
    }

    private fun move() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}