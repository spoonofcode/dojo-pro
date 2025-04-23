package com.spoonofcode.dojopro.feature.demo

import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class DemoViewState(
    override val isLoadingView: Boolean = false,
    val sportEvents: List<SportEvent> = emptyList(),
): BaseViewState()