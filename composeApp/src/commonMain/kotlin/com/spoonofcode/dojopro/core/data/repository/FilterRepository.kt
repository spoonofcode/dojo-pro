package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.ext.plus
import com.spoonofcode.dojopro.core.ext.roundToNextHour
import com.spoonofcode.dojopro.core.test.OpenForMokkery
import com.spoonofcode.dojopro.core.ui.utils.LocalDateTimeUtils
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState.Companion.ONE_WEEK_IN_HOURS
import kotlinx.datetime.LocalDateTime

data class FilterData(
    val selectedClubId: Int? = null,
    val selectedCoachId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedTypeId: Int? = null,
    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = ONE_WEEK_IN_HOURS)
        .roundToNextHour(),
)

@OpenForMokkery
class FilterRepository {

    private var selectedFilters = FilterData()

    fun setSelectedFilters(newFilterData: FilterData) {
        selectedFilters = newFilterData
    }

    fun getSelectedFilters(): FilterData = selectedFilters

}