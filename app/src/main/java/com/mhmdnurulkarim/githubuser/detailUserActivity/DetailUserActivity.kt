package com.mhmdnurulkarim.githubuser.detailUserActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.mhmdnurulkarim.core.data.Resource
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.SectionPagerAdapter
import com.mhmdnurulkarim.githubuser.databinding.ActivityDetailUserBinding
import com.mhmdnurulkarim.githubuser.utils.Const.EXTRA_USER
import com.mhmdnurulkarim.githubuser.utils.Const.TAB_TITLES
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel: DetailUserViewModel by viewModel()
    private var isFavorite = false

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
                    is Resource.Error -> onFailed(result.message)
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> {
                        onSuccess(result.data)
                        changedFavorite(isFavorite)
                        binding.fabFavorite.setOnClickListener{
                            result.data?.let { it1 -> addOrRemoveFavorite(it1) }
                            changedFavorite(isFavorite)
                        }
                    }
                }
            }
    }

    private fun onSuccess(data: User?) {
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

            data?.login?.let {
                detailViewModel.getFavoriteDetailState(it)
                    ?.observe(this@DetailUserActivity) { data ->
                        isFavorite = data.isFavorite == true
                        changedFavorite(isFavorite)
                    }
            }
        }
    }

    private fun addOrRemoveFavorite(data: User) {
        if (!isFavorite) {
            data.isFavorite = true
            detailViewModel.insertFavoriteUser(data)
            isFavorite = true
        } else {
            data.isFavorite = false
            detailViewModel.deleteFavoriteUser(data)
            isFavorite = false
        }
        changedFavorite(isFavorite)
    }

    private fun changedFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_favorite,
                    null
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_favorite_border,
                    null
                )
            )
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