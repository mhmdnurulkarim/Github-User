package com.mhmdnurulkarim.githubuser.ui.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityMainBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory
import com.mhmdnurulkarim.githubuser.ui.darkTheme.DarkThemeActivity
import com.mhmdnurulkarim.githubuser.ui.favoriteActivity.FavoriteActivity
import com.mhmdnurulkarim.githubuser.data.Result
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: UserAdapter
    private val mainViewModel: MainViewModel by viewModels{ ViewModelFactory.getInstance(this) }

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

        val names = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack")
        val randomName = names[Random.nextInt(names.size)]
        mainViewModel.searchUser(randomName).observe(this) { result ->
            when (result) {
                is Result.Error -> onFailed(result.error)
                is Result.Loading -> onLoading()
                is Result.Success -> onSuccess(result.data.items)
            }
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
//                    searchBar.text = searchView.text
                    searchView.hide()
                    mainViewModel.searchUser(searchView.text.toString()).observe(this@MainActivity){result ->
                        when (result) {
                            is Result.Error -> onFailed(result.error)
                            is Result.Loading -> onLoading()
                            is Result.Success -> onSuccess(result.data.items)
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
        Log.d(MainActivity::class.java.simpleName, message.toString())
    }
}