package com.spoonofcode.dojopro.feature.search.filter

import com.spoonofcode.dojopro.core.ext.plus
import com.spoonofcode.dojopro.core.ext.roundToNextHour
import com.spoonofcode.dojopro.core.ui.BaseViewState
import com.spoonofcode.dojopro.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

internal data class FilterViewState(
    override val isLoadingView: Boolean = true,
    val clubs: Map<Int, String> = emptyMap(),
    val coaches: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),
    val types: Map<Int, String> = emptyMap(),
    val selectedClubId: Int = ALL_OPTION_ID,
    val selectedCoachId: Int = ALL_OPTION_ID,
    val selectedLevelId: Int = ALL_OPTION_ID,
    val selectedTypeId: Int = ALL_OPTION_ID,

    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = ONE_WEEK_IN_HOURS).roundToNextHour(),
    ) : BaseViewState() {
    companion object {
        const val ALL_OPTION_ID = 0
        const val ALL_OPTION_NAME = "All"
        const val ONE_WEEK_IN_HOURS = 168
    }
}
