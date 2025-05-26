package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.TypeRepository
import com.spoonofcode.dojopro.core.model.Club
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Room
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFormData(
    val clubs: List<Club>,
    val rooms: List<Room>,
    val levels: List<Level>,
    val types: List<Type>,
    val sportEvent: SportEvent? = null
)

class LoadSportEventFormDataUseCase(
    private val clubRepository: ClubRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
    private val typeRepository: TypeRepository,
    private val sportEventRepository: SportEventRepository,
) {
    suspend operator fun invoke(
        screenMode: ScreenMode
    ): SportEventFormData {
        return coroutineScope {
            val clubsAsync = async { clubRepository.readAll() }
            val roomsAsync = async { roomRepository.readAll() }
            val levelsAsync = async { levelRepository.readAll() }
            val typesAsync = async { typeRepository.readAll() }

            var sportEvent: SportEvent? = null
            if (screenMode is ScreenMode.Edit) {
                val sportEventAsync =
                    async { sportEventRepository.read(id = screenMode.sportEventId) }
                sportEvent = sportEventAsync.await()
            }

            val clubs = clubsAsync.await()
            val rooms = roomsAsync.await()
            val levels = levelsAsync.await()
            val types = typesAsync.await()

            SportEventFormData(
                clubs = clubs,
                rooms = rooms,
                levels = levels,
                types = types,
                sportEvent = sportEvent
            )
        }
    }
}