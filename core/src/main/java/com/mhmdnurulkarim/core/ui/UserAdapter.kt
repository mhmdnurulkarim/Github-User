package com.mhmdnurulkarim.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mhmdnurulkarim.core.databinding.ItemUserBinding
import com.mhmdnurulkarim.core.domain.model.User

class UserAdapter(
    private val onClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataPosition = getItem(position)
        holder.setData(dataPosition)
    }

    inner class ListViewHolder(private val binding: ItemUserBinding, val onClick: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(user: User) {
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = user.login
            itemView.setOnClickListener {
                onClick(user)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}