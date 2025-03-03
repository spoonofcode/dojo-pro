package com.spoonofcode.dojopro.feature.search.filter

internal data class FilterViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val coaches: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),
    val selectedCoachId: Int = ALL_OPTION_ID,
    val selectedLevelId: Int = ALL_OPTION_ID,
) {
    companion object {
        const val ALL_OPTION_ID = 0
        const val ALL_OPTION_NAME = "All"
    }
}
