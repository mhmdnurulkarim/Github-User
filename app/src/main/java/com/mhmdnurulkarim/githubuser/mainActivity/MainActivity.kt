package com.mhmdnurulkarim.githubuser.mainActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.core.data.Resource
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.core.ui.UserAdapter
import com.mhmdnurulkarim.githubuser.R
import com.mhmdnurulkarim.githubuser.databinding.ActivityMainBinding
import com.mhmdnurulkarim.githubuser.detailUserActivity.DetailUserActivity
import com.mhmdnurulkarim.githubuser.utils.Const.EXTRA_USER
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: UserAdapter
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mLayoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)
        mAdapter = UserAdapter(
            onClick = { selectedData ->
                val intent = Intent(this, DetailUserActivity::class.java)
                intent.putExtra(EXTRA_USER, selectedData.login)
                startActivity(intent)
            }
        )

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
                is Resource.Error -> onFailed(result.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> onSuccess(result.data)
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
                                is Resource.Error -> onFailed(result.message)
                                is Resource.Loading -> onLoading()
                                is Resource.Success -> result.data?.let { onSuccess(it) }
                            }
                        }
                    false
                }

            searchBar.inflateMenu(R.menu.main_menu)
            searchBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_favorite -> {
                        val uri = Uri.parse("githubuser://favorite")
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }

                    R.id.menu_darkTheme -> {
//                        startActivity(Intent(this@MainActivity, DarkThemeActivity::class.java))
                        Toast.makeText(this@MainActivity, "InPorgress", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
    }

    private fun onSuccess(data: List<User>?) {
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