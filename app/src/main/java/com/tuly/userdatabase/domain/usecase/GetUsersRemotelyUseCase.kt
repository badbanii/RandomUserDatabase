package com.tuly.userdatabase.domain.usecase

import com.tuly.userdatabase.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersRemotelyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(numberOfUsers: Int) = userRepository.getUsersRemotely(numberOfUsers)
}