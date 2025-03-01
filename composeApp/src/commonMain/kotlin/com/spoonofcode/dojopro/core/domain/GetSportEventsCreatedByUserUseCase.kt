package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.network.SessionManager

class GetSportEventsCreatedByUserUseCase(
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): List<SportEvent> =
        sportEventRepository.readSportEventsCreatedByUser(sessionManager.getSessionUserId()!!)
}