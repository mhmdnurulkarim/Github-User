package com.mhmdnurulkarim.githubuser.ui.detailUserActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.adapter.SectionPagerAdapter
import com.mhmdnurulkarim.githubuser.data.Result
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityDetailUserBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory
import com.mhmdnurulkarim.githubuser.utils.Const.EXTRA_USER
import com.mhmdnurulkarim.githubuser.utils.Const.TAB_TITLES

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
        val pageAdapter = SectionPagerAdapter(this, username.toString())

        binding.apply {
            viewPager.adapter = pageAdapter
            TabLayoutMediator(tabs, viewPager) { tabs, position ->
                tabs.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        detailViewModel.getDetailUser(username.toString())
            .observe(this@DetailUserActivity) { result ->
                when (result) {
                    is Result.Error -> onFailed(result.error)
                    is Result.Loading -> onLoading()
                    is Result.Success -> onSuccess(result.data)
                }
            }
    }

    private fun onSuccess(data: DetailUserResponse?) {
        binding.progressBar.visibility = View.GONE
        binding.apply {
            detailName.text = data?.name
            detailUsername.text = data?.login
            detailNumberFollowers.text = data?.followers.toString()
            detailNumberFollowing.text = data?.following.toString()
            detailNumberRepo.text = data?.publicRepos.toString()

            binding.detailAvatar.load(data?.avatarUrl) {
                transformations(CircleCropTransformation())
            }

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