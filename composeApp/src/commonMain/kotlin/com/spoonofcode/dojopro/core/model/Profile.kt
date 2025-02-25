package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val firstName: String,
    val lastName: String,
    val numberOfEventsUserParticipatedIn: Long = 0L,
    val numberOfEventsCreatedByUser: Long,
)

@Serializable
data class ProfileRequest(
    val userId: Int,
)