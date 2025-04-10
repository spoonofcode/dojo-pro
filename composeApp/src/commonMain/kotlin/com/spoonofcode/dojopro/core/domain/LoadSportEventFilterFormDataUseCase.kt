package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.TypeRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.model.Club
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.core.model.User
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFilterFormData(
    val clubs: List<Club>,
    val coaches: List<User>,
    val levels: List<Level>,
    val types: List<Type>,
)

class LoadSportEventFilterFormDataUseCase(
    private val clubRepository: ClubRepository,
    private val levelRepository: LevelRepository,
    private val typeRepository: TypeRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): SportEventFilterFormData {
        return coroutineScope {
            val clubsAsync = async { clubRepository.readAll() }
            val coachesAsync = async { userRepository.readAllUsersByRole(role = Role.COACH) }
            val levelsAsync = async { levelRepository.readAll() }
            val typesAsync = async { typeRepository.readAll() }

            val clubs = clubsAsync.await()
            val coaches = coachesAsync.await()
            val levels = levelsAsync.await()
            val types = typesAsync.await()

            SportEventFilterFormData(
                clubs = clubs,
                coaches = coaches,
                levels = levels,
                types = types
            )
        }
    }
}