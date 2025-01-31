package core.repository

import SessionRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import network.HttpStatusCodes
import network.NetworkConfig
import org.koin.mp.KoinPlatform.getKoin

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

    override suspend fun create(request: RQ): RS {
        val response: HttpResponse = httpClient.post("${networkConfig.baseUrl}/$resourceName/") {
            getHeader(this)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request))
        }

        val responseBody = responseOrException(response).body<String>()

        return Json.decodeFromString(responseSerializer, responseBody)
    }

    override suspend fun read(id: Int): RS {
        val response: HttpResponse = httpClient.get("${networkConfig.baseUrl}/$resourceName/$id") {
            getHeader(this)
        }

        val responseBody = responseOrException(response).body<String>()

        return Json.decodeFromString(responseSerializer, responseBody)
    }

    override suspend fun update(id: Int, request: RQ): Boolean {
        val response: HttpResponse = httpClient.put("${networkConfig.baseUrl}/$resourceName/$id") {
            getHeader(this)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request)) // Serialize request
        }

        responseOrException(response).body<String>()
        return true
    }

    override suspend fun delete(id: Int): Boolean {
        val response: HttpResponse =
            httpClient.delete("${networkConfig.baseUrl}/$resourceName/$id") {
                getHeader(this)
            }

        responseOrException(response).body<String>()
        return true
    }

    override suspend fun readAll(): List<RS> {
        val response: HttpResponse = httpClient.get("${networkConfig.baseUrl}/$resourceName/") {
            getHeader(this)
        }
        val responseBody = responseOrException(response).body<String>()

        return Json.decodeFromString(
            ListSerializer(responseSerializer),
            responseBody
        )
    }

    private suspend fun getHeader(
        httpRequestBuilder: HttpRequestBuilder,
    ) {
        if (sessionTokenRequired) {
            httpRequestBuilder.header(
                "Authorization",
                "Bearer ${getJWTToken()}"
            )
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

    private suspend fun getJWTToken(): String {
        return sessionRepository.getSessionToken() ?: "TOKEN_NOT_FOUND"
    }
}