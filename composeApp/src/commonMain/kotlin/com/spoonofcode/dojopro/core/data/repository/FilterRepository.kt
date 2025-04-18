package com.spoonofcode.dojopro.core.data.repository

import kotlinx.datetime.LocalDateTime

data class FilterData(
    val selectedClubId: Int? = null,
    val selectedCoachId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedTypeId: Int? = null,
    val startDateTime: LocalDateTime? = null,
    val endDateTime: LocalDateTime? = null,
)

class FilterRepository {

    private var selectedFilters = FilterData()

    fun setSelectedFilters(newFilterData: FilterData) {
        selectedFilters = newFilterData
    }

    fun getSelectedFilters(): FilterData = selectedFilters

}