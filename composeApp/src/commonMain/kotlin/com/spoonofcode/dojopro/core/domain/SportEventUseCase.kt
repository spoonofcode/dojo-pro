package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.core.network.SessionManager
import kotlinx.datetime.LocalDateTime

internal class SportEventUseCase(
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun createSportEvent(
        title: String,
        description: String,
        minNumberOfPeople: Int,
        maxNumberOfPeople: Int,
        cost: String,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        selectedCoachId: Int,
        selectedRoomId: Int,
        selectedLevelId: Int,
    ) {
        sportEventRepository.create(
            request = SportEventRequest(
                title = title,
                description = description,
                minNumberOfPeople = minNumberOfPeople,
                maxNumberOfPeople = maxNumberOfPeople,
                cost = cost,
                startDateTime = startDateTime,
                endDateTime = endDateTime,
                coachId = selectedCoachId!!,
                roomId = selectedRoomId!!,
                typeId = 1,
                levelId = selectedLevelId!!,
                creatorUserId = sessionManager.getSessionUserId()!!,
            )
        )
    }

    suspend fun getSportEventById(sportEventId: Int): SportEvent =
        sportEventRepository.read(id = sportEventId)

    suspend fun editSportEvent(
        sportEventId: Int,
        title: String,
        description: String,
        minNumberOfPeople: Int,
        maxNumberOfPeople: Int,
        cost: String,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        selectedCoachId: Int,
        selectedRoomId: Int,
        selectedLevelId: Int,
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
                coachId = selectedCoachId!!,
                roomId = selectedRoomId!!,
                typeId = 1,
                levelId = selectedLevelId!!,
                creatorUserId = sessionManager.getSessionUserId()!!,
            )
        )
    }

    suspend fun deleteSportEvent(sportEventId: Int) {
        sportEventRepository.delete(
            id = sportEventId,
        )
    }
}