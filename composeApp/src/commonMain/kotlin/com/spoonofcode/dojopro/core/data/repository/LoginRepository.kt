package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.base.repository.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Login
import com.spoonofcode.dojopro.core.model.LoginRequest
import com.spoonofcode.dojopro.core.network.HttpStatusCodes
import com.spoonofcode.dojopro.core.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

class LoginRepository : GenericCrudRepository<LoginRequest, Login>(
    resourceName = "login",
    requestSerializer = LoginRequest.serializer(),
    responseSerializer = Login.serializer(),
    sessionTokenRequired = false,
) {

    private val httpClient: HttpClient by getKoin().inject()
    private val networkConfig: NetworkConfig by getKoin().inject()

    suspend fun refreshToken(jwtRefreshToken: String): Login {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = httpClient.post("${networkConfig.baseUrl}/refresh/") {
                header(HttpHeaders.Authorization, "Bearer $jwtRefreshToken")
            }

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Login.serializer(), responseBody)
        }
    }

    private fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES -> throw Exception("Client Error")
            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES -> throw Exception("Server Error")
            else -> unhandledException(response)
        }
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }
}