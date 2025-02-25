package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.network.SessionManager

class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Profile = profileRepository.read(
        id = sessionManager.getSessionUserId()!!
    )
}