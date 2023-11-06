package com.mhmdnurulkarim.githubuser.ui.favoriteActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.dataStore.Resource
import com.mhmdnurulkarim.githubuser.databinding.ActivityFavoriteBinding
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.ViewStateCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity(), ViewStateCallback<List<DetailUserResponse>> {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mAdapter: UserAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mLayoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)
        mAdapter = UserAdapter()

        binding.contentRecyclerView.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }

        CoroutineScope(Dispatchers.Main).launch {
            favoriteViewModel.getFavoriteList().observe(this@FavoriteActivity) {
                when (it) {
                    is Resource.Error -> onFailed(it.message)
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            favoriteViewModel.getFavoriteList().observe(this@FavoriteActivity) {
                when (it) {
                    is Resource.Error -> onFailed(it.message)
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                }
            }
        }
    }

    override fun onSuccess(data: List<DetailUserResponse>){
        mAdapter.submitList(data)
        binding.contentRecyclerView.apply {
            progressBar.visibility = invisible
            rvMain.visibility = visible
        }
    }

    override fun onLoading() {
        binding.contentRecyclerView.apply {
            progressBar.visibility = visible
            rvMain.visibility = invisible
        }
    }

    override fun onFailed(message: String?) {
        binding.contentRecyclerView.apply {
            progressBar.visibility = invisible
            rvMain.visibility = invisible
        }
        Log.d(FavoriteActivity::class.java.simpleName, message.toString())
    }
}