package com.mhmdnurulkarim.githubuser

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mhmdnurulkarim.githubuser.followersFragment.FollowersFragment
import com.mhmdnurulkarim.githubuser.followingFragment.FollowingFragment
import com.mhmdnurulkarim.githubuser.utils.Const.TAB_TITLES

class SectionPagerAdapter(activity: AppCompatActivity, private val username: String) :
    FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> FollowersFragment.getInstance(username)
            else -> FollowingFragment.getInstance(username)
        }
        return fragment
    }

    override fun getItemCount(): Int = TAB_TITLES.size
}