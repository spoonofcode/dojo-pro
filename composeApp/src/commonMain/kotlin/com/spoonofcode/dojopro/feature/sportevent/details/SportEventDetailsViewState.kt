package com.spoonofcode.dojopro.feature.sportevent.details

import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SportEventDetailsViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val sportEvent: SportEvent? = null,
) : BaseViewState()