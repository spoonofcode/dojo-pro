package com.spoonofcode.dojopro.feature.appsettings

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class AppSettingsViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val isSearchUserButtonVisible: Boolean = false,
) : BaseViewState()