package com.spoonofcode.dojopro.feature.demo

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class DemoViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val sportEvents: List<SportEvent> = emptyList(),
)