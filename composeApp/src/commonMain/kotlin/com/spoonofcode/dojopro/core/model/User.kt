package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val nickName: String? = null,
    val fullName: String = buildString {
        append("$firstName $lastName")
        nickName?.let { append(" ($it)") }
    },
    val email: String,
    val role: Role = Role.USER,
)

@Serializable
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
)