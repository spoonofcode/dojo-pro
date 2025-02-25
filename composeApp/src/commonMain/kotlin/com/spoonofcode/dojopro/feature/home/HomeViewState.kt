package com.spoonofcode.dojopro.feature.home

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class HomeViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val sportEventsUserParticipatedIn: List<SportEvent> = emptyList(),
    val sportEventsCreatedByUser: List<SportEvent> = emptyList(),
)