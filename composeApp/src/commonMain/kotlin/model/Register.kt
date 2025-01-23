package model

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val jwtToken: String
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)