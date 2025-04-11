package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val id: Int,
    val name: String,
    val location: String,
)