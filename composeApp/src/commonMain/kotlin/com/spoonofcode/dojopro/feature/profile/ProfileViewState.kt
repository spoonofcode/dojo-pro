package com.spoonofcode.dojopro.feature.profile

import com.spoonofcode.dojopro.core.model.Profile

internal data class ProfileViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val profile: Profile? = null,
)