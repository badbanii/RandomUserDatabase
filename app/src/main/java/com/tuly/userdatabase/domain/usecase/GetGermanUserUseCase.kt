package com.tuly.userdatabase.domain.usecase

import com.tuly.userdatabase.domain.repository.UserRepository
import javax.inject.Inject

class GetGermanUserUseCase @Inject constructor(
    private val userRepository: UserRepository) {
    suspend fun execute() = userRepository.getGermanUser()
}