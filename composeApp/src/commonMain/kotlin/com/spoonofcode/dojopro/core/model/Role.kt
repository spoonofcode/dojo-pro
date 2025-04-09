package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    ADMIN,
    CLUB_OWNER,
    COACH,
    USER,
}