package model

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)