package com.mhmdnurulkarim.githubuser.ui.followersFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.githubuser.adapter.UserAdapter
import com.mhmdnurulkarim.githubuser.data.Result
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.FragmentFollowersBinding
import com.mhmdnurulkarim.githubuser.ui.ViewModelFactory

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
        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
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

        followersViewModel.getUserFollowers(username.toString()).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> onFailed(it.error)
                is Result.Loading -> onLoading()
                is Result.Success -> onSuccess(it.data)
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
        Log.d(FollowersFragment::class.java.simpleName, message.toString())
    }
}