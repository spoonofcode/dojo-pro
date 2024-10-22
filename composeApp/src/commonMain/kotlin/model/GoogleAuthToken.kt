package model

import kotlinx.serialization.Serializable

@Serializable
data class GoogleAuthToken(
    val idToken: String
)