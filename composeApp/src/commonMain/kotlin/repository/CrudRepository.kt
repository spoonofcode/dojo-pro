package repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
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
    private val responseSerializer: KSerializer<RS>
) : CrudRepository<RQ, RS> {

    protected val httpClient: HttpClient by getKoin().inject()

    override suspend fun create(request: RQ): RS {
        val responseBody: String = httpClient.post("$BASE_URL/$resourceName/") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request)) // Serialize request
        }.body() // Get response as String

        println("BARTEK responseBody = $responseBody")

        return Json.decodeFromString(responseSerializer, responseBody) // Deserialize response
    }

    override suspend fun read(id: Int): RS {
        val responseBody: String = httpClient.get("$BASE_URL/$resourceName/$id")
            .body() // Get response as String

        return Json.decodeFromString(responseSerializer, responseBody) // Deserialize response
    }

    override suspend fun update(id: Int, request: RQ): Boolean {
        httpClient.put("$BASE_URL/$resourceName/$id") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestSerializer, request)) // Serialize request
        }
        return true // Simplified for this example
    }

    override suspend fun delete(id: Int): Boolean {
        httpClient.delete("$BASE_URL/$resourceName/$id")
        return true // Simplified for this example
    }

    override suspend fun readAll(): List<RS> {
        val responseBody: String = httpClient.get("$BASE_URL/$resourceName/")
            .body() // Get response as String

        return Json.decodeFromString(
            ListSerializer(responseSerializer),
            responseBody
        ) // Deserialize list of responses
    }
}