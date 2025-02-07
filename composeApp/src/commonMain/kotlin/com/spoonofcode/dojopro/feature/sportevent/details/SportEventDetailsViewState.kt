package com.spoonofcode.dojopro.feature.sportevent.details

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class SportEventDetailsViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val sportEvent: SportEvent? = null,
)