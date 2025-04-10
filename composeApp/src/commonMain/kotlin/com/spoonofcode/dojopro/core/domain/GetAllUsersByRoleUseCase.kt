package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.User

class GetAllUsersByRoleUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        role: Role
    ): List<User> = userRepository.readAllUsersByRole(role = role)
}