package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.network.SessionManager

class GetSportEventsUserParticipatedInUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): List<SportEvent> =
        userRepository.readSportEventsUserParticipatedIn(sessionManager.getSessionUserId()!!)
}