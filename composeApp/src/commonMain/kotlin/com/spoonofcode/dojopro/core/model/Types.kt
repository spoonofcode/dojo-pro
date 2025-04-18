package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Types(val id: Int) {
    DOJO_BOXING_TRAINING(id = 1),
    DOJO_GRAPPLING_TRAINING(id = 2),
    DOJO_YOUTH_TRAINING(id = 3),
    DOJO_BEGINNERS_GROUP_TRAINING(id = 4),
    DOJO_BEGINNERS_TRAINING(id = 5),
    DOJO_INDIVIDUAL_TRAINING(id = 6),
    DOJO_MOTOR_TRAINING(id = 7),
    DOJO_OPEN_TRAINING(id = 8),
    DOJO_MAT_TRAINING(id = 9),
    DOJO_ADVANCED_GROUP_TRAINING(id = 10),
}