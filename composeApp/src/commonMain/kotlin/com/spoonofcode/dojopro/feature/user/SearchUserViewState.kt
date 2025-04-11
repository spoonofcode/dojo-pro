package com.spoonofcode.dojopro.feature.user

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SearchUserViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    val isUpdateUsersButtonVisible: Boolean = false,
): BaseViewState()