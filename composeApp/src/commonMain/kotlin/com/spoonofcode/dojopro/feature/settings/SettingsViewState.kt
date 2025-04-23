package com.spoonofcode.dojopro.feature.settings

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SettingsViewState(
    override val isLoadingView: Boolean = false,
    val isSearchUserButtonVisible: Boolean = false,
): BaseViewState()