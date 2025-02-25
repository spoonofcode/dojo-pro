package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEvent

class GetAllSportEvents(
    private val sportEventRepository: SportEventRepository,
) {
    suspend operator fun invoke(
        sportEventId: Int
    ): List<SportEvent> = sportEventRepository.readAll()
}