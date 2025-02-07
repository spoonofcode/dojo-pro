package core.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class LoginGoogleRequest(
    val googleUserToken: String
)