package model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
)

@Serializable
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
)