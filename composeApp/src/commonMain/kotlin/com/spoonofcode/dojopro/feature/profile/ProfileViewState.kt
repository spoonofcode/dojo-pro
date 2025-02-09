package com.spoonofcode.dojopro.feature.profile

import com.spoonofcode.dojopro.core.model.Profile

internal data class ProfileViewState(
    val title: String = "Profile title",
    val profile: Profile? = null,
)