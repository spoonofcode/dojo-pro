package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Refresh(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)