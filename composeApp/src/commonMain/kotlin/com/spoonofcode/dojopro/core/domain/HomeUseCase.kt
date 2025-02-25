package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.network.SessionManager

internal class HomeUseCase(
    private val sportEventRepository: SportEventRepository,
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun getAllSportEvents(): List<SportEvent> {
        return sportEventRepository.readAll()
    }

    suspend fun getSportEventsUserParticipatedIn(): List<SportEvent> {
        return userRepository.readSportEventsUserParticipatedIn(sessionManager.getSessionUserId()!!)
    }

    suspend fun getSportEventsCreatedByUser(): List<SportEvent> {
        return sportEventRepository.readSportEventsCreatedByUser(sessionManager.getSessionUserId()!!)
    }
}