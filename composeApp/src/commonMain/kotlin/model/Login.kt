package model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val jwtToken: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)