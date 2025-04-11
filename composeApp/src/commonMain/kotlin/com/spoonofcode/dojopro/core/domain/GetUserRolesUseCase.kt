package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RoleRepository
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.network.SessionManager

class GetUserRolesUseCase(
    private val roleRepository: RoleRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): List<Role> = roleRepository.readAllRolesByUserId(
        userId = sessionManager.getSessionUserId()!!
    )
}