package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ProfileRepositoryInterface

class GetProfileUseCase(
    private val profileRepository: ProfileRepositoryInterface,
//    private val sessionManager: SessionManager,
) {
//    suspend operator fun invoke(): Profile = profileRepository.read(
//        id = sessionManager.getSessionUserId()!!
//    )

    suspend operator fun invoke(): String = profileRepository.testBartek()
}