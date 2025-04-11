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
            val matchesClubFilter = selectedFilter.selectedClubId == null ||
                    selectedFilter.selectedClubId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.club.id == selectedFilter.selectedClubId
            val matchesCoachFilter = selectedFilter.selectedCoachId == null ||
                    selectedFilter.selectedCoachId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.creatorUser.id == selectedFilter.selectedCoachId
            val matchesLevelFilter = selectedFilter.selectedLevelId == null ||
                    selectedFilter.selectedLevelId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.level.id == selectedFilter.selectedLevelId
            val matchesTypeFilter = selectedFilter.selectedTypeId == null ||
                    selectedFilter.selectedTypeId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.type.id == selectedFilter.selectedTypeId
            matchesClubFilter && matchesCoachFilter && matchesLevelFilter && matchesTypeFilter
        }
    }
}

