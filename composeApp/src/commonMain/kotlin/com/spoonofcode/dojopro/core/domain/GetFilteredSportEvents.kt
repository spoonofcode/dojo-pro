package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.FilterRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState

class GetFilteredSportEvents(
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(
        sportEvents: List<SportEvent>,
    ): List<SportEvent> {
        val selectedFilter = filterRepository.getSelectedFilters()
        return sportEvents.filter { sportEvent ->
            val matchesCoachFilter = selectedFilter.selectedCoachId == null ||
                    selectedFilter.selectedCoachId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.coach.id == selectedFilter.selectedCoachId
            val matchesLevelFilter = selectedFilter.selectedLevelId == null ||
                    selectedFilter.selectedLevelId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.level.id == selectedFilter.selectedLevelId
            matchesCoachFilter && matchesLevelFilter
        }
    }
}

