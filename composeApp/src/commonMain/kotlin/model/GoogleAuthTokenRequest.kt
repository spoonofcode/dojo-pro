package model

import kotlinx.serialization.Serializable

@Serializable
data class GoogleAuthTokenRequest(
    val idToken: String
)