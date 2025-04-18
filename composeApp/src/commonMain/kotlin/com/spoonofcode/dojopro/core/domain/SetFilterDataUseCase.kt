package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.data.repository.FilterRepository
import kotlinx.datetime.LocalDateTime

class SetFilterDataUseCase(
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(
        selectedClubId: Int? = null,
        selectedCoachId: Int? = null,
        selectedLevelId: Int? = null,
        selectedTypeId: Int? = null,
        startDateTime: LocalDateTime? = null,
        endDateTime: LocalDateTime? = null,
    ) {
        filterRepository.setSelectedFilters(
            newFilterData = FilterData(
                selectedClubId = selectedClubId,
                selectedCoachId = selectedCoachId,
                selectedLevelId = selectedLevelId,
                selectedTypeId = selectedTypeId,
                startDateTime = startDateTime,
                endDateTime = endDateTime,
            )
        )
    }
}