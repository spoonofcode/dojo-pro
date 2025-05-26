package com.spoonofcode.dojopro.feature.search.search

import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SearchViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val searchText: String = "",
    val initSportEvents: List<SportEvent> = emptyList(),
    val filteredSportEvents: List<SportEvent> = emptyList(),
) : BaseViewState()