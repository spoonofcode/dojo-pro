package core.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)