package model

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val id: Int,
    val name: String,
)