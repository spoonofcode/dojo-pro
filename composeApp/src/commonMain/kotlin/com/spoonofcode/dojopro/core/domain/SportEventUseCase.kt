package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.Coach
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Room
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.core.network.SessionManager
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.LocalDateTime

data class SportEventFormData(
    val coaches: List<Coach>,
    val rooms: List<Room>,
    val levels: List<Level>,
    val sportEvent: SportEvent? = null
)

internal class SportEventUseCase(
    private val coachRepository: CoachRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
    private val sportEventRepository: SportEventRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun loadSportEventFormData(screenMode: ScreenMode): SportEventFormData {
        return coroutineScope {
            val coachesAsync = async { coachRepository.readAll() }
            val roomsAsync = async { roomRepository.readAll() }
            val levelsAsync = async { levelRepository.readAll() }

            var sportEvent: SportEvent? = null
            if (screenMode is ScreenMode.Edit) {
                val sportEventAsync =
                    async { getSportEventById(sportEventId = screenMode.sportEventId) }
                sportEvent = sportEventAsync.await()
            }

            val coaches = coachesAsync.await()
            val rooms = roomsAsync.await()
            val levels = levelsAsync.await()

            SportEventFormData(coaches, rooms, levels, sportEvent)
        }
    }

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