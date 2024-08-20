package model

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: Int,
    val name: String,
)