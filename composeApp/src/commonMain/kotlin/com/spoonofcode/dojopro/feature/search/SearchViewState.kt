package com.spoonofcode.dojopro.feature.search

import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SearchViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = true,
    val searchText: String = "",
    val filteredSportEvents: List<SportEvent> = emptyList(),
): BaseViewState()