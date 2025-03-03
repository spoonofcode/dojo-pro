package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.model.Coach
import com.spoonofcode.dojopro.core.model.Level
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFilterFormData(
    val coaches: List<Coach>,
    val levels: List<Level>,
)

class LoadSportEventFilterFormDataUseCase(
    private val coachRepository: CoachRepository,
    private val levelRepository: LevelRepository,
) {
    suspend operator fun invoke(): SportEventFilterFormData {
        return coroutineScope {
            val coachesAsync = async { coachRepository.readAll() }
            val levelsAsync = async { levelRepository.readAll() }

            val coaches = coachesAsync.await()
            val levels = levelsAsync.await()

            SportEventFilterFormData(coaches, levels)
        }
    }
}