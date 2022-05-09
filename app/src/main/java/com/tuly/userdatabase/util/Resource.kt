package com.tuly.userdatabase.util

import com.tuly.userdatabase.presentation.userlist.model.UserView

sealed class Resource {
    object Loading : Resource()

    class Results(val users: List<UserView>) : Resource()

    class Error(val errorMessage: String?) : Resource()
}