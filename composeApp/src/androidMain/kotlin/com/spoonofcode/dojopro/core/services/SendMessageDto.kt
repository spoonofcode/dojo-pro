package com.spoonofcode.dojopro.core.services

data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody,
)

data class NotificationBody(
    val title: String,
    val body: String,
)