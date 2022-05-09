package com.tuly.userdatabase.data.repository

import com.tuly.userdatabase.data.local.UserDao
import com.tuly.userdatabase.data.mapper.UserMapper
import com.tuly.userdatabase.data.remote.api.UserApi
import com.tuly.userdatabase.data.remote.dto.UserDto
import com.tuly.userdatabase.domain.model.UserInfo
import com.tuly.userdatabase.domain.repository.UserRepository
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUsersRemotely(numberOfUsers: Int) {
        try {
            val newUsers = withTimeout(5000) {
                userApi.getUsers(numberOfUsers)
            }.results.map { userMapper.mapUserDtoToUserEntity(it) }
            userDao.insertUsers(newUsers)
        } catch (error: Exception) {
            throw RuntimeException()
        }
    }

    override suspend fun getUsersLocally() = userDao.getAllUsers()?.map { userMapper.mapUserEntityUserInfo(it) }

    override suspend fun getGermanUser(): UserInfo { return userMapper.mapGermanUserDtoToUserInfo(userApi.getGermanUser().results[0]) }

}