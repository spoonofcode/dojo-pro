package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.User

class GetAllUsersByRoleUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        roleId: Int
    ): List<User> = userRepository.readAllUsersByRole(roleId = roleId)
}