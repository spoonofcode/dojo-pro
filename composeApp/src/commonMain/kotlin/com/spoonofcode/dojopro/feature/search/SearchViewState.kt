package com.spoonofcode.dojopro.feature.search

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class SearchViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val searchText: String = "",
    val allSportEvents: List<SportEvent> = emptyList(),
    val filteredSportEvents: List<SportEvent> = emptyList(),
)