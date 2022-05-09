package com.tuly.userdatabase.presentation.userlist.mapper

import com.tuly.userdatabase.domain.model.UserInfo
import com.tuly.userdatabase.presentation.userlist.model.UserView
import javax.inject.Inject

class UserViewMapper @Inject constructor() {
    fun mapUserInfoToUserView(userInfo: UserInfo)= UserView(
        userInfo.email,
        userInfo.gender,
        userInfo.address,
        userInfo.pictureLarge,
        userInfo.username,
        userInfo.date,
        userInfo.title,
        userInfo.nat
    )
}