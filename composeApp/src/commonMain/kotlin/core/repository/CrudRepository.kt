package core.repository

import SessionRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import network.HttpStatusCodes
import network.NetworkConfig
import org.koin.mp.KoinPlatform.getKoin
import tabs.login.TokenUseCase

interface CrudRepository<RQ, RS> {
    suspend fun create(request: RQ): RS
    suspend fun read(id: Int): RS
    suspend fun update(id: Int, request: RQ): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun readAll(): List<RS>
}

abstract class GenericCrudRepository<RQ : Any, RS : Any>(
    private val resourceName: String,
    private val requestSerializer: KSerializer<RQ>,
    private val responseSerializer: KSerializer<RS>,
    private val sessionTokenRequired: Boolean = true,
) : CrudRepository<RQ, RS> {
    private val httpClient: HttpClient by getKoin().inject()
    private val networkConfig: NetworkConfig by getKoin().inject()
    private val sessionRepository: SessionRepository by getKoin().inject()
    private val tokenUseCase: TokenUseCase by getKoin().inject()

    override suspend fun create(request: RQ): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Post,
                sessionTokenRequired = sessionTokenRequired
            ) {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(requestSerializer, request))
            }

            val responseBody = responseOrException(response).body<String>()

            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun read(id: Int): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            ) {
                contentType(ContentType.Application.Json)
            }

            val responseBody = responseOrException(response).body<String>()

            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun update(id: Int, request: RQ): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Put,
                sessionTokenRequired = sessionTokenRequired
            ) {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(requestSerializer, request))
            }

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Delete,
                sessionTokenRequired = sessionTokenRequired
            ) {
                contentType(ContentType.Application.Json)
            }

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun readAll(): List<RS> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            ) {
                contentType(ContentType.Application.Json)
            }

            val responseBody = responseOrException(response).body<String>()

            Json.decodeFromString(
                ListSerializer(responseSerializer),
                responseBody
            )
        }
    }

    private fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES ->
                throw Exception("Client Error ${response.status}")

            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES ->
                throw Exception("Server Error ${response.status}")

            else -> unhandledException(response)
        }
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }

    private fun getJwtAccessToken(): String {
        return sessionRepository.getSessionAccessToken() ?: "TOKEN_NOT_FOUND"
    }

    /**
     * A generic function that attempts a request with the current access token,
     * retries once upon 401 (after refresh), and returns the response as HttpResponse.
     *
     * You can then deserialize the HttpResponse to whatever data type you need.
     */
    private suspend fun safeApiCall(
        client: HttpClient,
        block: HttpRequestBuilder.() -> Unit
    ): HttpResponse {
        // 1) First attempt
        var response: HttpResponse = client.request {
            block()
            header(HttpHeaders.Authorization, "Bearer ${sessionRepository.getSessionAccessToken()}")
        }

        // 2) If 401, try refresh once
        if (response.status == Unauthorized) {
            val refreshed = tokenUseCase.refreshAccessToken() // your refresh function
            if (!refreshed) {
                // Refresh failed => no valid session
                throw Exception("Client Error")
            }

            // If refresh succeeded => try the same request again
            response = client.request {
                block()
                header(HttpHeaders.Authorization, "Bearer ${sessionRepository.getSessionAccessToken()}")
            }

            // If still 401 => forced to log out
            if (response.status == Unauthorized) {
                throw Exception("Client Error")
            }
        }

        return response
    }

    private suspend fun doRequest(
        urlPath: String,
        method: HttpMethod,
        sessionTokenRequired: Boolean,
        block: HttpRequestBuilder.() -> Unit
    ): HttpResponse {
        return if (sessionTokenRequired) {
            // Authenticated request with safeApiCall
            safeApiCall(httpClient) {
                url("${networkConfig.baseUrl}/$urlPath")
                this.method = method
                block()
            }
        } else {
            // Unauthenticated direct request
            httpClient.request {
                url("${networkConfig.baseUrl}/$urlPath")
                this.method = method
                block()
            }
        }
    }
}