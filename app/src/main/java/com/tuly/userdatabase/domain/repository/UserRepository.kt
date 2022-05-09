package com.tuly.userdatabase.domain.repository

import com.tuly.userdatabase.domain.model.UserInfo

interface UserRepository {
    suspend fun getUsersRemotely(numberOfUsers:Int)
    suspend fun getUsersLocally():List<UserInfo>?
    suspend fun getGermanUser():UserInfo
}