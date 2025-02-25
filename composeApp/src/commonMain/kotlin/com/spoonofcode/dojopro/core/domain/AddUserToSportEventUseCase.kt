package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.network.SessionManager

class AddUserToSportEventUseCase(
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        sportEventId: Int
    ) {
        sportEventRepository.addUserToSportEvent(
            userId = sessionManager.getSessionUserId()!!,
            sportEventId = sportEventId,
        )
    }
}