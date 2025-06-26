package com.spoonofcode.dojopro.core.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageFCM(
    val to: String?,
    val notification: NotificationBody,
)

@Serializable
data class NotificationBody(
    val title: String,
    val body: String,
)