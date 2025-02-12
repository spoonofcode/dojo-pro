package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val firstName: String,
    val lastName: String,
    val numberOfEventsIParticipatedIn: Long = 0L,
    val numberOfCreatedEvents: Long,
)

@Serializable
data class ProfileRequest(
    val userId: Int,
)