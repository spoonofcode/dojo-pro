package com.spoonofcode.dojopro.feature.sportevent.edit

import com.spoonofcode.dojopro.core.ext.plus
import com.spoonofcode.dojopro.core.ext.roundToNextHour
import com.spoonofcode.dojopro.core.ui.BaseViewState
import com.spoonofcode.dojopro.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

internal data class SportEventEditViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val screenMode: ScreenMode = ScreenMode.Create,
    val title: String = "",
    val description: String = "",

    val coaches: Map<Int, String> = emptyMap(),
    val rooms: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),

    val eventTitle: String? = null,
    val eventDescription: String? = null,
    val selectedCoachId: Int? = null,
    val selectedRoomId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedMinNumberOfPeople: Int = DEFAULT_MIN_NUMBER_OF_PEOPLE,
    val selectedMaxNumberOfPeople: Int = DEFAULT_MAX_NUMBER_OF_PEOPLE,

    val cost: String = "",

    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = 1).roundToNextHour(),
) : BaseViewState() {
    companion object {
        private const val DEFAULT_MIN_NUMBER_OF_PEOPLE = 4
        private const val DEFAULT_MAX_NUMBER_OF_PEOPLE = 8
    }
}