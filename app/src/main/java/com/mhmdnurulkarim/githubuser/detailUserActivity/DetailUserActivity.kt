package com.mhmdnurulkarim.githubuser.detailUserActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityDetailUserBinding
import com.mhmdnurulkarim.githubuser.ViewModelFactory
import com.mhmdnurulkarim.core.utils.Const.EXTRA_USER
import com.mhmdnurulkarim.core.utils.Const.TAB_TITLES

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel: DetailUserViewModel by viewModels {
        ViewModelFactory.getInstance(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USER)
        val pageAdapter = com.mhmdnurulkarim.core.ui.SectionPagerAdapter(this, username.toString())

        binding.apply {
            viewPager.adapter = pageAdapter
            TabLayoutMediator(tabs, viewPager) { tabs, position ->
                tabs.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        detailViewModel.getDetailUser(username.toString())
            .observe(this@DetailUserActivity) { result ->
                when (result) {
                    is com.mhmdnurulkarim.core.data.Result.Error -> onFailed(result.error)
                    is com.mhmdnurulkarim.core.data.Result.Loading -> onLoading()
                    is com.mhmdnurulkarim.core.data.Result.Success -> onSuccess(result.data)
                }
            }
    }

    private fun onSuccess(data: GithubUserResponse?) {
        binding.progressBar.visibility = View.GONE
        binding.apply {
            detailName.text = data?.name
            detailUsername.text = data?.login
            detailNumberFollowers.text = data?.followers.toString()
            detailNumberFollowing.text = data?.following.toString()
            detailNumberRepo.text = data?.publicRepos.toString()

            Glide.with(this@DetailUserActivity)
                .load(data?.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.detailAvatar)

            if (data?.isFavorite == true) {
                fabFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_favorite,
                        null
                    )
                )
            } else {
                fabFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_favorite_border,
                        null
                    )
                )
            }

            fabFavorite.setOnClickListener {
                if (data?.isFavorite == true) {
                    data.isFavorite = false
                    detailViewModel.deleteFavoriteUser(data)
                    fabFavorite.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_favorite_border,
                            null
                        )
                    )
                } else {
                    data?.isFavorite = true
                    data?.let { it1 -> detailViewModel.insertFavoriteUser(it1) }
                    fabFavorite.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_favorite,
                            null
                        )
                    )
                }
            }
        }
    }

    private fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onFailed(message: String?) {
        binding.progressBar.visibility = View.VISIBLE
        Log.d(DetailUserActivity::class.java.simpleName, message.toString())
    }
}