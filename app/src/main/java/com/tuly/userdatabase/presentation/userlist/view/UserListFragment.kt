package com.tuly.userdatabase.presentation.userlist.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tuly.userdatabase.R
import com.tuly.userdatabase.databinding.DialogUserDetailsBinding
import com.tuly.userdatabase.databinding.FragmentUserListBinding
import com.tuly.userdatabase.presentation.userlist.adapter.OnItemClickListener
import com.tuly.userdatabase.presentation.userlist.adapter.UserAdapter
import com.tuly.userdatabase.presentation.userlist.model.UserView
import com.tuly.userdatabase.presentation.userlist.viewmodel.UserListViewModel
import com.tuly.userdatabase.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserListFragment :Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private var shouldOpenDialog:Boolean = false
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int get() = linearLayoutManager.findLastVisibleItemPosition()
    private lateinit var userAdapter: UserAdapter
    private val viewModel: UserListViewModel by viewModels()
    private var userClicked=false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.loadUsers()
        viewModel.userListViewStateLiveData.observe(viewLifecycleOwner) { display(it) }

        lifecycleScope.launchWhenStarted {
            viewModel.userClicked.collectLatest { status ->
                userClicked=status
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.currentUser.collectLatest { data->
                if(userClicked)
                {
                    showDialog(data)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun showDialog(user:UserView)
    {
        val userDialog: Dialog? = activity?.let { Dialog(it) }
        val userDialogBinding= DialogUserDetailsBinding.inflate(layoutInflater,null,false)
        userDialog?.setContentView(userDialogBinding.root)
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.7).toInt()
        userDialog?.window?.setLayout(width,height)
        userDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if(user.nat=="UK")
        {
            val germanUser:UserView = viewModel.getGermanUser()
            userDialogBinding.textviewEmailTextTarget.text=germanUser.email
            userDialogBinding.textviewDateTextTarget.text=germanUser.date
            userDialogBinding.textviewGenderTextTarget.text=germanUser.gender
            userDialogBinding.textviewDialogNameTarget.text=germanUser.username
            with(userDialogBinding)
            {
                Glide.with(root.context).load(user.pictureLarge).placeholder(R.drawable.img).into(userDialogBinding.imageviewDialogUser)
            }
        }
        else
        {
            userDialogBinding.textviewEmailTextTarget.text=user.email
            userDialogBinding.textviewDateTextTarget.text=user.date
            userDialogBinding.textviewGenderTextTarget.text=user.gender
            userDialogBinding.textviewDialogNameTarget.text=user.username
            with(userDialogBinding)
            {
                Glide.with(root.context).load(user.pictureLarge).placeholder(R.drawable.img).into(userDialogBinding.imageviewDialogUser)
            }
        }
        userDialogBinding.buttonDialogClose.setOnClickListener {
            userDialog?.dismiss()
        }
        userDialog?.show()
    }

    private fun initViews()
    {
        initRecyclerView()
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                viewModel.onScroll( totalItemCount, lastVisibleItemPosition)
            }
        })
    }

    private fun initRecyclerView()
    {
        userAdapter = UserAdapter(
            onItemClickListener = object : OnItemClickListener {
                override fun onItemClick(user: UserView, imageView: ImageView) {
                    viewModel.onUserClicked(user, imageView)
                }
            }
        )

        linearLayoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun display(state: Resource)
    {
        return when (state) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Results -> {
                hideLoading()
                displayUsers(state.users)
            }
            is Resource.Error -> {
                hideLoading()
                showErrorMessage(state.errorMessage)
            }
        }
    }

    private fun showLoading()
    {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading()
    {
        binding.progressBar.visibility = View.GONE
    }

    private fun displayUsers(userList: List<UserView>)
    {
        userAdapter.submitList(userList)
    }

    private fun showErrorMessage(errorMessage: String?)
    {
        errorMessage?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}