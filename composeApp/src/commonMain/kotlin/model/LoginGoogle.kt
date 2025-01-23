package model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val jwtToken: String
)

@Serializable
data class LoginGoogleRequest(
    val googleUserToken: String
)