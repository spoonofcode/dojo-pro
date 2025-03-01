package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository

class DeleteSportEventUseCase(
    private val sportEventRepository: SportEventRepository,
) {
    suspend operator fun invoke(
        sportEventId: Int
    ) {
        sportEventRepository.delete(
            id = sportEventId,
        )
    }
}