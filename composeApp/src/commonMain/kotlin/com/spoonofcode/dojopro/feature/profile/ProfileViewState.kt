package com.spoonofcode.dojopro.feature.profile

import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ProfileViewState(
    override val isLoadingView: Boolean = false,
    val profile: Profile? = null,
) : BaseViewState(
    isLoadingView = isLoadingView,
)