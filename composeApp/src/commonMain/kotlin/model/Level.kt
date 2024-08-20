package model

import kotlinx.serialization.Serializable

@Serializable
data class Level(
    val id: Int,
    val name: String,
)