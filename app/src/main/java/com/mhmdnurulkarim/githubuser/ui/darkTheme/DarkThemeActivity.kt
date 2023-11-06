package com.mhmdnurulkarim.githubuser.ui.darkTheme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.mhmdnurulkarim.githubuser.databinding.ActivityDarkThemeBinding

class DarkThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkThemeBinding
    private val darkViewModel: DarkThemeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            darkViewModel.saveThemeSetting(isChecked)
        }

        darkViewModel.getThemeSetting().observe(this) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }
    }
}