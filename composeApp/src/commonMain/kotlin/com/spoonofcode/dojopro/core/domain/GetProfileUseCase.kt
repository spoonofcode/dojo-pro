package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ProfileRepositoryBartek
import com.spoonofcode.dojopro.core.network.SessionManager

class GetProfileUseCase(
    private val profileRepository: ProfileRepositoryBartek,
    private val sessionManager: SessionManager,
) {
//    suspend operator fun invoke(): Profile = profileRepository.read(
//        id = sessionManager.getSessionUserId()!!
//    )

    suspend operator fun invoke(): String = profileRepository.testBartek()
}