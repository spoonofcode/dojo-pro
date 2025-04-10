package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Role(val id: Int) {
    ADMIN(id = 1),
    CLUB_OWNER(id = 2),
    COACH(id = 3),
    USER(id = 4),
}