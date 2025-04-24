package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.core.network.SessionManager
import kotlinx.datetime.LocalDateTime


class EditSportEventUseCase(
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        sportEventId: Int,
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
    ) {
        sportEventRepository.update(
            id = sportEventId,
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
                typeId = selectedTypeId,
                levelId = selectedLevelId,
                creatorUserId = sessionManager.getSessionUserId()!!,
            )
        )
    }
}