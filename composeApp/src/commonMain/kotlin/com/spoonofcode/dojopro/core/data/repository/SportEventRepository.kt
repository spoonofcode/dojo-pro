package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(),
    responseSerializer = SportEvent.serializer(),
) {
//    private val httpClient: HttpClient by getKoin().inject()
//    private val networkConfig: NetworkConfig by getKoin().inject()
//
//    suspend fun readSportEventsCreatedByMe(userId: Int): Refresh {
//        return withContext(Dispatchers.IO) {
//            val response: HttpResponse = httpClient.post("${networkConfig.baseUrl}/sportEvents/") {
//                header(HttpHeaders.Authorization, "Bearer $jwtRefreshToken")
//            }
//
//            val responseBody = responseOrException(response).body<String>()
//            Json.decodeFromString(Refresh.serializer(), responseBody)
//        }
//    }
//
//    private fun responseOrException(response: HttpResponse): HttpResponse {
//        return when (response.status) {
//            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
//            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES -> throw Exception("Client Error")
//            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES -> throw Exception("Server Error")
//            else -> unhandledException(response)
//        }
//    }
//
//    private fun unhandledException(response: HttpResponse): HttpResponse {
//        throw Exception("Unhandled Error $response")
//    }
}