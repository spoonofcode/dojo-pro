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
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
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
        val responseBody: String = httpClient.post("${networkConfig.baseUrl}/$resourceName/") {
            getHeader(this)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request)) // Serialize request
        }.body() // Get response as String

        return Json.decodeFromString(responseSerializer, responseBody) // Deserialize response
    }

    override suspend fun read(id: Int): RS {
        val responseBody: String = httpClient.get("${networkConfig.baseUrl}/$resourceName/$id") {
            getHeader(this)
        }
            .body() // Get response as String

        return Json.decodeFromString(responseSerializer, responseBody) // Deserialize response
    }

    override suspend fun update(id: Int, request: RQ): Boolean {
        httpClient.put("${networkConfig.baseUrl}/$resourceName/$id") {
            getHeader(this)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request)) // Serialize request
        }
        return true // Simplified for this example
    }

    override suspend fun delete(id: Int): Boolean {
        httpClient.delete("${networkConfig.baseUrl}/$resourceName/$id") {
            getHeader(this)
        }
        return true // Simplified for this example
    }

    override suspend fun readAll(): List<RS> {
        val responseBody: String = httpClient.get("${networkConfig.baseUrl}/$resourceName/") {
            getHeader(this)
        }
            .body() // Get response as String

        return Json.decodeFromString(
            ListSerializer(responseSerializer),
            responseBody
        ) // Deserialize list of responses
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

    private suspend fun getJWTToken(): String {
        return sessionRepository.getSessionToken() ?: "TOKEN_NOT_FOUND"
    }
}