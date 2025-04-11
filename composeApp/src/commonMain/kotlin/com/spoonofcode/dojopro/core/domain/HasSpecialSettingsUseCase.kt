package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RoleRepository
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.network.SessionManager

class HasSpecialSettingsUseCase(
    private val roleRepository: RoleRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Boolean {
        val roles = roleRepository.readAllRolesByUserId(
            userId = sessionManager.getSessionUserId()!!
        )

        return roles.any { it.id == Roles.ADMIN.id || it.id == Roles.CLUB_OWNER.id }
    }
}