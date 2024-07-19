package model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: Int,
    val firstName: String,
    val lastName: String,
)