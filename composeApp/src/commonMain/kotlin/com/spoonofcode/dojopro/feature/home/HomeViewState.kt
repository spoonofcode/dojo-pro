package com.spoonofcode.dojopro.feature.home

import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class HomeViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val sportEventsUserParticipatedIn: List<SportEvent> = emptyList(),
    val sportEventsCreatedByUser: List<SportEvent> = emptyList(),
) : BaseViewState()