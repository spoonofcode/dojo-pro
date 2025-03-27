package com.spoonofcode.dojopro.feature.search.filter

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class FilterViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = true,
    val coaches: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),
    val selectedCoachId: Int = ALL_OPTION_ID,
    val selectedLevelId: Int = ALL_OPTION_ID,
) : BaseViewState() {
    companion object {
        const val ALL_OPTION_ID = 0
        const val ALL_OPTION_NAME = "All"
    }
}
