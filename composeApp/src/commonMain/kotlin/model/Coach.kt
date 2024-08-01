package model

import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String = "$firstName $lastName"
)