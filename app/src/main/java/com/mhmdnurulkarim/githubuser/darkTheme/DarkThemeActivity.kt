package com.mhmdnurulkarim.githubuser.darkTheme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mhmdnurulkarim.githubuser.databinding.ActivityDarkThemeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DarkThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val darkViewModel: DarkThemeViewModel by viewModel()

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