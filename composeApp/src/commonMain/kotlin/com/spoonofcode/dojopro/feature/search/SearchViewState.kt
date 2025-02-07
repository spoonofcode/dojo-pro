package com.spoonofcode.dojopro.feature.search

import com.spoonofcode.dojopro.core.model.SportEvent

internal data class SearchViewState(
    val sportEvents: List<SportEvent> = emptyList(),
)