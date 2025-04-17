package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.User

class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        userId: Int
    ): User = userRepository.read(id = userId)
}