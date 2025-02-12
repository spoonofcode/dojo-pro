package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.network.SessionManager

internal class ProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun getProfile(): Profile {
        return profileRepository.read(
            id = sessionManager.getSessionUserId()!!
        )
    }
}