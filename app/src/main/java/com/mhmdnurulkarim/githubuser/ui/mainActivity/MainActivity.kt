package com.mhmdnurulkarim.githubuser.ui.mainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.Result
import com.mhmdnurulkarim.githubuser.data.model.FakeNameData.names
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ActivityMainBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory
import com.mhmdnurulkarim.githubuser.ui.component.OptionMenu
import com.mhmdnurulkarim.githubuser.ui.component.Search
import com.mhmdnurulkarim.githubuser.ui.darkTheme.DarkThemeActivity
import com.mhmdnurulkarim.githubuser.ui.favoriteActivity.FavoriteActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: UserAdapter
    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory.getInstance(this) }

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

        val randomName = names[Random.nextInt(names.size)]
        mainViewModel.searchUser(randomName).observe(this) { result ->
            when (result) {
                is Result.Error -> onFailed(result.error)
                is Result.Loading -> onLoading()
                is Result.Success -> onSuccess(result.data.items)
            }
        }

        binding.appBarCompose.setContent {
            val context = LocalContext.current
            val queryUser by mainViewModel.queryUser

            AppBarItem(
                query = queryUser,
                onQueryChange = mainViewModel::searchUser,
                navigateToFavorite = {
                    moveToFavorite(context)
                },
                navigateToProfile = {

                },
                navigateToSettings = {
                    moveToSettings(context)
                }
            )

            mainViewModel.getUser(queryUser).observe(this) { result ->
                when (result) {
                    is Result.Error -> onFailed(result.error)
                    is Result.Loading -> onLoading()
                    is Result.Success -> onSuccess(result.data.items)
                }
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
                    R.id.menu_profile -> {

                    }
                }
                true
            }
        }
    }

    private fun onSuccess(data: List<DetailUserResponse>) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarItem(
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToFavorite: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Search(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier.weight(1f)
        )
        OptionMenu(
            navigateToFavorite = navigateToFavorite,
            navigateToProfile = navigateToProfile,
            navigateToSettings = navigateToSettings
        )
    }
}

private fun moveToFavorite(context: Context) {
    context.startActivity(Intent(context, FavoriteActivity::class.java))
}

private fun moveToSettings(context: Context) {
    context.startActivity(Intent(context, DarkThemeActivity::class.java))
}