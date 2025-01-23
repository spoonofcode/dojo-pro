package model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val idToken: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)