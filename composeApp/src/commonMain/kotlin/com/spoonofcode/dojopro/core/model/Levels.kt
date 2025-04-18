package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Levels(val id: Int) {
    BEGINNER(1),
    BASIC(id = 2),
    ADVANCE(id = 3),
    PRO(id = 4),
}