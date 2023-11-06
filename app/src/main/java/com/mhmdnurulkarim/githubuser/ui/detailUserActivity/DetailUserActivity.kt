package com.mhmdnurulkarim.githubuser.ui.detailUserActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.adapter.SectionPagerAdapter
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.dataStore.Resource
import com.mhmdnurulkarim.githubuser.databinding.ActivityDetailUserBinding
import com.mhmdnurulkarim.githubuser.utils.Const.EXTRA_USER
import com.mhmdnurulkarim.githubuser.utils.Const.TAB_TITLES
import com.mhmdnurulkarim.githubuser.utils.ViewStateCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserActivity : AppCompatActivity(), ViewStateCallback<DetailUserResponse?> {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel: DetailUserViewModel by viewModels()

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

        CoroutineScope(Dispatchers.Main).launch {
            onLoading()
            detailViewModel.getDetailUser(username.toString()).observe(this@DetailUserActivity) {
                when (it) {
                    is Resource.Error -> onFailed(it.message)
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> onSuccess(it.data)
                }
            }
        }
    }

    override fun onSuccess(data: DetailUserResponse?) {
        binding.progressBar.visibility = invisible
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

            if (data?.isFavorite == true){
                fabFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, null))
            } else {
                fabFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border, null))
            }

            fabFavorite.setOnClickListener {
                if (data?.isFavorite == true) {
                    data.isFavorite = false
                    detailViewModel.deleteFavoriteUser(data)
                    fabFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border, null))
                } else {
                    data?.isFavorite = true
                    data?.let { it1 -> detailViewModel.insertFavoriteUser(it1) }
                    fabFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, null))
                }
            }
        }
    }

    override fun onLoading() {
        binding.progressBar.visibility = visible
    }

    override fun onFailed(message: String?) {
        binding.progressBar.visibility = visible
        Log.d(DetailUserActivity::class.java.simpleName, message.toString())
    }
}