package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericBaseRepository
import com.spoonofcode.dojopro.core.model.MessageFCM
import com.spoonofcode.dojopro.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@OpenForMokkery
class MessageFCMRepository : GenericBaseRepository<MessageFCM, MessageFCM>(
    resourceName = "messageFCM",
    requestSerializer = MessageFCM.serializer(),
    responseSerializer = MessageFCM.serializer(),
) {

    suspend fun sendMessage(body: MessageFCM) {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "messageFCM/send",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    MessageFCM.serializer(),
                    MessageFCM(
                        to = body.to,
                        notification = body.notification
                    )
                ),
            )
            responseOrException(response).body<String>()
        }
    }

}