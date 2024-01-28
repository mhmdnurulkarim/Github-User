package com.mhmdnurulkarim.githubuser.mainActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityMainBinding
import com.mhmdnurulkarim.githubuser.ViewModelFactory
import com.mhmdnurulkarim.githubuser.darkTheme.DarkThemeActivity
import com.mhmdnurulkarim.favorite.FavoriteActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: com.mhmdnurulkarim.core.ui.UserAdapter
    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mLayoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)
        mAdapter = com.mhmdnurulkarim.core.ui.UserAdapter()

        binding.contentRecyclerView.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        val names = listOf(
            "Alice",
            "Bob",
            "Charlie",
            "David",
            "Eve",
            "Frank",
            "Grace",
            "Hannah",
            "Ivy",
            "Jack"
        )
        val randomName = names[Random.nextInt(names.size)]
        mainViewModel.searchUser(randomName).observe(this) { result ->
            when (result) {
                is com.mhmdnurulkarim.core.data.Result.Error -> onFailed(result.error)
                is com.mhmdnurulkarim.core.data.Result.Loading -> onLoading()
                is com.mhmdnurulkarim.core.data.Result.Success -> onSuccess(result.data.items)
            }
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
//                    searchBar.text = searchView.text
                    searchView.hide()
                    mainViewModel.searchUser(searchView.text.toString())
                        .observe(this@MainActivity) { result ->
                            when (result) {
                                is com.mhmdnurulkarim.core.data.Result.Error -> onFailed(result.error)
                                is com.mhmdnurulkarim.core.data.Result.Loading -> onLoading()
                                is com.mhmdnurulkarim.core.data.Result.Success -> onSuccess(result.data.items)
                            }
                        }
                    false
                }

            searchBar.inflateMenu(R.menu.main_menu)
            searchBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_favorite -> {
                        startActivity(Intent(this@MainActivity, com.mhmdnurulkarim.favorite.FavoriteActivity::class.java))
                    }

                    R.id.menu_darkTheme -> {
                        startActivity(Intent(this@MainActivity, DarkThemeActivity::class.java))
                    }
                }
                true
            }
        }
    }

    private fun onSuccess(data: List<GithubUserResponse>) {
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
        Log.d(MainActivity::class.java.simpleName, message.toString())
    }
}