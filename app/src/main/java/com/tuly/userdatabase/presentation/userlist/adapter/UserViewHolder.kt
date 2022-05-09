package com.tuly.userdatabase.presentation.userlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuly.userdatabase.R
import com.tuly.userdatabase.databinding.ItemUserPreviewBinding
import com.tuly.userdatabase.presentation.userlist.model.UserView

class UserViewHolder(root: View, private val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(root) {

    private val userViewBinding = ItemUserPreviewBinding.bind(root)

    fun bindData(user: UserView) {
        with(userViewBinding) {
            textviewLocation.text = user.address
            textviewUsername.text=user.username
            root.setOnClickListener{ onItemClickListener.onItemClick(user, imageviewUser) }
            Glide.with(root.context).load(user.pictureLarge).placeholder(R.drawable.img).into(userViewBinding.imageviewUser)
        }
    }
}
