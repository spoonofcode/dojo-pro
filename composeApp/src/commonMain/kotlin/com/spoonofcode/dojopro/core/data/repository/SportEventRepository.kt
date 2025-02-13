package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(),
    responseSerializer = SportEvent.serializer(),
) {
    suspend fun readSportEventsCreatedByUser(creatorUserId: Int): List<SportEvent> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "sportEvents",
                method = HttpMethod.Get,
                queryParams = mapOf("creatorUserId" to creatorUserId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(SportEvent.serializer()), responseBody)
        }
    }
}