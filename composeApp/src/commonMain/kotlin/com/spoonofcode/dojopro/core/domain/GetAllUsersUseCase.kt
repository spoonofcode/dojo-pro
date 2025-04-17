package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.User

class GetAllUsersUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): List<User> = userRepository.readAll()
}