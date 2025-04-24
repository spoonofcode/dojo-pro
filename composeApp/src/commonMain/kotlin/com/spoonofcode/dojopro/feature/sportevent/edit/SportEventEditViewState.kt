package com.spoonofcode.dojopro.feature.sportevent.edit

import com.spoonofcode.dojopro.core.ext.plus
import com.spoonofcode.dojopro.core.ext.roundToNextHour
import com.spoonofcode.dojopro.core.ui.BaseViewState
import com.spoonofcode.dojopro.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

internal data class SportEventEditViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val screenMode: ScreenMode = ScreenMode.Create,
    val title: String = "",
    val description: String = "",

    val clubs: Map<Int, String> = emptyMap(),
    val coaches: Map<Int, String> = emptyMap(),
    val rooms: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),
    val types: Map<Int, String> = emptyMap(),

    val eventTitle: String? = null,
    val eventDescription: String? = null,
    val selectedClubId: Int? = null,
    val selectedRoomId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedTypeId: Int? = null,
    val selectedMinNumberOfPeople: Int = DEFAULT_MIN_NUMBER_OF_PEOPLE,
    val selectedMaxNumberOfPeople: Int = DEFAULT_MAX_NUMBER_OF_PEOPLE,

    val cost: String = "",

    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = 1).roundToNextHour(),
) : BaseViewState() {
    companion object {
        private const val DEFAULT_MIN_NUMBER_OF_PEOPLE = 8
        private const val DEFAULT_MAX_NUMBER_OF_PEOPLE = 12
    }
}