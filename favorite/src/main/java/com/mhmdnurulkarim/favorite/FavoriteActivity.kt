package com.mhmdnurulkarim.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.core.ui.UserAdapter
import com.mhmdnurulkarim.favorite.databinding.ActivityFavoriteBinding
import com.mhmdnurulkarim.favorite.di.favoriteModule
import com.mhmdnurulkarim.githubuser.detailUserActivity.DetailUserActivity
import com.mhmdnurulkarim.githubuser.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.Const
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mAdapter: UserAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        observeFavoriteList()

        val mLayoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)
        mAdapter = UserAdapter(
            onClick = { selectedData ->
                val intent = Intent(this, DetailUserActivity::class.java)
                intent.putExtra(Const.EXTRA_USER, selectedData.login)
                startActivity(intent)
            }
        )

        binding.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }

    private fun observeFavoriteList() {
        lifecycleScope.launch {
            favoriteViewModel.getFavoriteList().observe(this@FavoriteActivity) { data ->
                if (data.isEmpty()) {
                    onLoading()
                } else {
                    onSuccess(data)
                }
            }
        }
    }

    private fun onSuccess(data: List<User>) {
        mAdapter.submitList(data)
        binding.apply {
            progressBar.visibility = View.GONE
            rvMain.visibility = View.VISIBLE
        }
    }

    private fun onLoading() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            rvMain.visibility = View.GONE
        }
    }
}