package com.tuly.userdatabase.data.remote.api

import com.tuly.userdatabase.data.remote.dto.UserDto
import com.tuly.userdatabase.data.remote.dto.UserListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("?")
    suspend fun getUsers(@Query("results") numberOfUsers: Int): UserListDto

    @GET("?nat=DE,results=1")
    suspend fun getGermanUser(): UserListDto
}