package com.spoonofcode.dojopro.core.data.repository

data class FilterData(
    val selectedClubId: Int? = null,
    val selectedCoachId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedTypeId: Int? = null,
)

class FilterRepository {

    private var selectedFilters = FilterData()

    fun setSelectedFilters(newFilterData: FilterData) {
        selectedFilters = newFilterData
    }

    fun getSelectedFilters(): FilterData = selectedFilters

}