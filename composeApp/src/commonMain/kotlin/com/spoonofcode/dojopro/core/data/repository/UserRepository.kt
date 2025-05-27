package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.AddRoleToUserRequest
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.model.UserRequest
import com.spoonofcode.dojopro.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
) {
    suspend fun readAllUsersByRole(roleId: Int): List<User> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users",
                method = HttpMethod.Get,
                queryParams = mapOf("roleId" to roleId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(User.serializer()), responseBody)
        }
    }

    suspend fun readSportEventsUserParticipatedIn(userId: Int): List<SportEvent> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/sportEvents",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(SportEvent.serializer()), responseBody)
        }
    }

    suspend fun addRoleToUser(roleId: Int, userId: Int) {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/roles",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    AddRoleToUserRequest.serializer(),
                    AddRoleToUserRequest(roleId = roleId)
                ),
            )
            responseOrException(response).body<String>()
        }
    }
}