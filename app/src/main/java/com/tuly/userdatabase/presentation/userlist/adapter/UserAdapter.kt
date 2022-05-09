package com.tuly.userdatabase.presentation.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tuly.userdatabase.R
import com.tuly.userdatabase.presentation.userlist.model.UserView

class UserAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<UserView, UserViewHolder>(UserCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_preview, parent, false)
        return UserViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    override fun getItemCount() = currentList.size
}

private class UserCallback : DiffUtil.ItemCallback<UserView>() {
    override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem == newItem
    }
}

interface OnItemClickListener {
    fun onItemClick(user: UserView, imageView: ImageView)
}
