package com.tuly.userdatabase.presentation.userlist.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuly.userdatabase.domain.usecase.GetGermanUserUseCase
import com.tuly.userdatabase.domain.usecase.GetUsersLocallyUseCase
import com.tuly.userdatabase.domain.usecase.GetUsersRemotelyUseCase
import com.tuly.userdatabase.presentation.userlist.mapper.UserViewMapper
import com.tuly.userdatabase.presentation.userlist.model.UserView
import com.tuly.userdatabase.util.Constants.NUMBER_OF_USERS_TO_LOAD
import com.tuly.userdatabase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersRemotelyUseCase: GetUsersRemotelyUseCase,
    private val getUsersLocallyUseCase: GetUsersLocallyUseCase,
    private val getGermanUserUseCase: GetGermanUserUseCase,
    private val userViewMapper:UserViewMapper,
    private val coroutineDispatcher: CoroutineDispatcher
):ViewModel(){

    private val emptyUser=UserView("happyduck216@example.com","female","Calle de Toledo 7979 Palma de Mallorca Castilla la Mancha 20536","https://randomuser.me/api/portraits/women/7.jpg",
        "happyduck216","1975-12-05","","Es")

    private val userListViewState: MutableLiveData<Resource> = MutableLiveData()
    val userListViewStateLiveData: LiveData<Resource> = userListViewState

    private val _currentUser: MutableStateFlow<UserView> = MutableStateFlow(emptyUser)
    val currentUser = _currentUser.asStateFlow()

    private val _userClicked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val userClicked = _userClicked.asStateFlow()

    private var isRequestingUsers = false

    fun loadUsers() {
        userListViewState.value=Resource.Loading
        viewModelScope.launch {
            try {
                withContext(coroutineDispatcher) {
                    isRequestingUsers = true
                    getUsersRemotelyUseCase.execute(NUMBER_OF_USERS_TO_LOAD)
                }
                val userList = getLocalUsers()
                userListViewState.value=Resource.Results(userList)
            } catch (error: RuntimeException) {
                userListViewState.value=Resource.Error("Error")
            } finally {
                isRequestingUsers = false
            }
        }
    }

    private suspend fun getLocalUsers() =
        withContext(coroutineDispatcher) {
            getUsersLocallyUseCase.execute()?.map {
                userViewMapper.mapUserInfoToUserView(it)
            } ?: emptyList()
        }

    suspend fun getGermanUser(): UserView
    {
        return userViewMapper.mapUserInfoToUserView(getGermanUserUseCase.execute())
    }

    fun onUserClicked(user: UserView, imageView: ImageView) {
        viewModelScope.launch{
            _currentUser.emit(user)
            _userClicked.emit(true)
        }

    }

    fun onScroll(totalItemCount: Int, lastVisibleItemPosition: Int) {
        if (!isRequestingUsers && totalItemCount == lastVisibleItemPosition + 1) {
            loadUsers()
        }
    }


}