package model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val idToken: String
)

@Serializable
data class LoginGoogleRequest(
    val googleIdToken: String
)