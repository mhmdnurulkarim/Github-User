package com.mhmdnurulkarim.githubuser.ui.favoriteActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.Result
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityFavoriteBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mAdapter: UserAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels{ ViewModelFactory.getInstance(this) }

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
                    is Result.Error -> onFailed(it.error)
                    is Result.Loading -> onLoading()
                    is Result.Success -> onSuccess(it.data)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            favoriteViewModel.getFavoriteList().observe(this@FavoriteActivity) {
                when (it) {
                    is Result.Error -> onFailed(it.error)
                    is Result.Loading -> onLoading()
                    is Result.Success -> onSuccess(it.data)
                }
            }
        }
    }

    private fun onSuccess(data: List<DetailUserResponse>){
        mAdapter.submitList(data)
        binding.contentRecyclerView.apply {
            progressBar.visibility = View.GONE
            rvMain.visibility = View.VISIBLE
        }
    }

    private fun onLoading() {
        binding.contentRecyclerView.apply {
            progressBar.visibility = View.VISIBLE
            rvMain.visibility = View.GONE
        }
    }

    private fun onFailed(message: String?) {
        binding.contentRecyclerView.apply {
            progressBar.visibility = View.GONE
            rvMain.visibility = View.GONE
        }
        Log.d(FavoriteActivity::class.java.simpleName, message.toString())
    }
}