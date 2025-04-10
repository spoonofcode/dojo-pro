package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.TypeRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.Club
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.Room
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFormData(
    val clubs: List<Club>,
    val coaches: List<User>,
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
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        screenMode: ScreenMode
    ): SportEventFormData {
        return coroutineScope {
            val clubsAsync = async { clubRepository.readAll() }
            val coachesAsync = async { userRepository.readAllUsersByRole(role = Role.COACH) }
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
            val coaches = coachesAsync.await()
            val rooms = roomsAsync.await()
            val levels = levelsAsync.await()
            val types = typesAsync.await()

            SportEventFormData(
                clubs = clubs,
                coaches = coaches,
                rooms = rooms,
                levels = levels,
                types = types,
                sportEvent = sportEvent
            )
        }
    }
}