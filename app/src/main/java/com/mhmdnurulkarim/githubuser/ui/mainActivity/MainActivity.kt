package com.mhmdnurulkarim.githubuser.ui.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.dataStore.Resource
import com.mhmdnurulkarim.githubuser.databinding.ActivityMainBinding
import com.mhmdnurulkarim.githubuser.ui.darkTheme.DarkThemeActivity
import com.mhmdnurulkarim.githubuser.ui.favoriteActivity.FavoriteActivity
import com.mhmdnurulkarim.githubuser.utils.ViewStateCallback

class MainActivity : AppCompatActivity(), ViewStateCallback<List<DetailUserResponse>> {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: UserAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mLayoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)
        mAdapter = UserAdapter()

        binding.contentRecyclerView.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        mainViewModel.searchUser("karim").observe(this){
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    mainViewModel.searchUser(searchView.text.toString()).observe(this@MainActivity){
                        when (it) {
                            is Resource.Error -> onFailed(it.message)
                            is Resource.Loading -> onLoading()
                            is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                        }
                    }
                    false
                }

            searchBar.inflateMenu(R.menu.main_menu)
            searchBar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_favorite -> {
                        startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
                    }
                    R.id.menu_darkTheme -> {
                        startActivity(Intent(this@MainActivity, DarkThemeActivity::class.java))
                    }
                }
                true
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
        Log.d(MainActivity::class.java.simpleName, message.toString())
    }
}