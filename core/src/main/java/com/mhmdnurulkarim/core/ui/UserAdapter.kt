package com.mhmdnurulkarim.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.databinding.ItemUserBinding
import com.mhmdnurulkarim.githubuser.ui.detailUserActivity.DetailUserActivity
import com.mhmdnurulkarim.core.utils.Const.EXTRA_USER

class UserAdapter : ListAdapter<DetailUserResponse, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataPosition = getItem(position)
        holder.setData(dataPosition)
    }

    inner class ListViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(user: DetailUserResponse) {
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = user.login
            itemView.setOnClickListener {
                val intentToDetail = Intent(itemView.context, DetailUserActivity::class.java)
                intentToDetail.putExtra(EXTRA_USER, user.login)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailUserResponse>() {
            override fun areItemsTheSame(
                oldItem: DetailUserResponse,
                newItem: DetailUserResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailUserResponse,
                newItem: DetailUserResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}