package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.Coach
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Room
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFormData(
    val coaches: List<Coach>,
    val rooms: List<Room>,
    val levels: List<Level>,
    val sportEvent: SportEvent? = null
)

class LoadSportEventFormDataUseCase(
    private val coachRepository: CoachRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
    private val sportEventRepository: SportEventRepository,
) {
    suspend operator fun invoke(
        screenMode: ScreenMode
    ): SportEventFormData {
        return coroutineScope {
            val coachesAsync = async { coachRepository.readAll() }
            val roomsAsync = async { roomRepository.readAll() }
            val levelsAsync = async { levelRepository.readAll() }

            var sportEvent: SportEvent? = null
            if (screenMode is ScreenMode.Edit) {
                val sportEventAsync =
                    async { sportEventRepository.read(id = screenMode.sportEventId) }
                sportEvent = sportEventAsync.await()
            }

            val coaches = coachesAsync.await()
            val rooms = roomsAsync.await()
            val levels = levelsAsync.await()

            SportEventFormData(coaches, rooms, levels, sportEvent)
        }
    }
}