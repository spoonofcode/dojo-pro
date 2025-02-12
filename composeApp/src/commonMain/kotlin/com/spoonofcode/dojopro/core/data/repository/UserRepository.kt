package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.model.UserRequest
import com.spoonofcode.dojopro.core.network.NetworkConfig
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
) {
    private val networkConfig: NetworkConfig by getKoin().inject()

    suspend fun readSportEventsIParticipatedIn(userId: Int): List<SportEvent> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/sportEvents",
                method = HttpMethod.Get,
                sessionTokenRequired = true,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(SportEvent.serializer()), responseBody)
        }
    }
}