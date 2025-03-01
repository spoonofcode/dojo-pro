package com.spoonofcode.dojopro.feature.search

import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.model.SportEvent

internal data class SearchViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val sportEvents: List<SportEvent> = emptyList(),
    val filterData: FilterData = FilterData(),
)