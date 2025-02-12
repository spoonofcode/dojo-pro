package com.spoonofcode.dojopro.feature.home

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class HomeViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val allSportEvents: List<SportEvent> = emptyList(),
    val sportEventsCreatedByMe: List<SportEvent> = emptyList(),
    val sportEventsIParticipatedIn: List<SportEvent> = emptyList(),
)