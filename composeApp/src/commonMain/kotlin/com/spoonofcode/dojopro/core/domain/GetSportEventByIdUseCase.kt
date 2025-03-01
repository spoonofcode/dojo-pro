package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEvent

class GetSportEventByIdUseCase(
    private val sportEventRepository: SportEventRepository,
) {
    suspend operator fun invoke(
        sportEventId: Int
    ): SportEvent =
        sportEventRepository.read(id = sportEventId)
}