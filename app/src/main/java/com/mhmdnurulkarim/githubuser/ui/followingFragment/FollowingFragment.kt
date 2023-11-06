package com.mhmdnurulkarim.githubuser.ui.followingFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.dataStore.Resource
import com.mhmdnurulkarim.githubuser.databinding.FragmentFollowingBinding
import com.mhmdnurulkarim.githubuser.ui.mainActivity.MainActivity
import com.mhmdnurulkarim.githubuser.utils.ViewStateCallback

class FollowingFragment : Fragment(), ViewStateCallback<List<DetailUserResponse>> {

    companion object {
        private const val KEY_BUNDLE = "USERNAME"

        fun getInstance(username: String): Fragment {
            return FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE, username)
                }
            }
        }
    }

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding as FragmentFollowingBinding
    private val followingViewModel: FollowingViewModel by viewModels()
    private lateinit var mAdapter: UserAdapter
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
        _binding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = LinearLayoutManager(view.context)
        val itemDecoration = DividerItemDecoration(view.context, mLayoutManager.orientation)
        mAdapter = UserAdapter()

        binding.contentRecyclerView.rvMain.apply {
            layoutManager = mLayoutManager
            addItemDecoration(itemDecoration)
            adapter = mAdapter
        }

        followingViewModel.getUserFollowing(username.toString()).observe(viewLifecycleOwner){
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
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