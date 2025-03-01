package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.data.repository.FilterRepository

class GetFilterDataUseCase(
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(
        selectedCoachId: Int? = null,
        selectedLevelId: Int? = null,
    ): FilterData {
        return filterRepository.getSelectedFilters()
    }
}