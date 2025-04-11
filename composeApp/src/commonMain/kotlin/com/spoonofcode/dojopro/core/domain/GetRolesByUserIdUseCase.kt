package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RoleRepository
import com.spoonofcode.dojopro.core.model.Role

class GetRolesByUserIdUseCase(
    private val roleRepository: RoleRepository,
) {
    suspend operator fun invoke(
        userId: Int,
    ): List<Role> = roleRepository.readAllRolesByUserId(
        userId = userId
    )
}