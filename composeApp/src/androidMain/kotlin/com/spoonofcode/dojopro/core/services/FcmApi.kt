package com.spoonofcode.dojopro.core.services

import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApi {

    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )

    @POST("/brodcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )

}