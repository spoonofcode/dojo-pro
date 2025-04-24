package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.core.network.SessionManager
import kotlinx.datetime.LocalDateTime


class CreateSportEventUseCase(
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        minNumberOfPeople: Int,
        maxNumberOfPeople: Int,
        cost: String,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        selectedClubId: Int,
        selectedRoomId: Int,
        selectedLevelId: Int,
        selectedTypeId: Int,
    ): SportEvent {
        return sportEventRepository.create(
            request = SportEventRequest(
                title = title,
                description = description,
                minNumberOfPeople = minNumberOfPeople,
                maxNumberOfPeople = maxNumberOfPeople,
                cost = cost,
                startDateTime = startDateTime,
                endDateTime = endDateTime,
                clubId = selectedClubId,
                roomId = selectedRoomId,
                levelId = selectedLevelId,
                typeId = selectedTypeId,
                creatorUserId = sessionManager.getSessionUserId()!!,
            )
        )
    }
}