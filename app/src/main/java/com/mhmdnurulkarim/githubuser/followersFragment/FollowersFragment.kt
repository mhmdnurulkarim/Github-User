package com.mhmdnurulkarim.githubuser.followersFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.core.data.source.remote.response.GithubUserResponse
import com.mhmdnurulkarim.githubuser.databinding.FragmentFollowersBinding
import com.mhmdnurulkarim.githubuser.ViewModelFactory

class FollowersFragment : Fragment() {

    companion object {
        private const val KEY_BUNDLE = "USERNAME"

        fun getInstance(username: String): Fragment {
            return FollowersFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE, username)
                }
            }
        }
    }

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding as FragmentFollowersBinding
    private val followersViewModel: FollowersViewModel by viewModels {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    }
    private lateinit var mAdapter: com.mhmdnurulkarim.core.ui.UserAdapter
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(KEY_BUNDLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = LinearLayoutManager(view.context)
        val itemDecoration = DividerItemDecoration(view.context, mLayoutManager.orientation)
        mAdapter = com.mhmdnurulkarim.core.ui.UserAdapter()

        binding.contentRecyclerView.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        followersViewModel.getUserFollowers(username.toString()).observe(viewLifecycleOwner) {
            when (it) {
                is com.mhmdnurulkarim.core.data.Result.Error -> onFailed(it.error)
                is com.mhmdnurulkarim.core.data.Result.Loading -> onLoading()
                is com.mhmdnurulkarim.core.data.Result.Success -> onSuccess(it.data)
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
        Log.d(FollowersFragment::class.java.simpleName, message.toString())
    }
}