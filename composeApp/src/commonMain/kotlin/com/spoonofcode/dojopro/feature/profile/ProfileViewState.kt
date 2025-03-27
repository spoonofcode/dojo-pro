package com.spoonofcode.dojopro.feature.profile

import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ProfileViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val profile: Profile? = null,
) : BaseViewState(
    isEnableView = isEnableView,
    isLoadingView = isLoadingView,
    isErrorView = isErrorView
)