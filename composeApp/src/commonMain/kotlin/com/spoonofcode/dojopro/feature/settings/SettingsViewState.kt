package com.spoonofcode.dojopro.feature.settings

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SettingsViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val isSearchUserButtonVisible: Boolean = false,
) : BaseViewState()