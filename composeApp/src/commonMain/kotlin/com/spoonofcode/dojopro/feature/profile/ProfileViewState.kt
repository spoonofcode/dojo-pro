package com.spoonofcode.dojopro.feature.profile

import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ProfileViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val profile: Profile? = null,
) : BaseViewState()