package repository

import fakeData.getFakeSportEvents
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.Profile
import model.SportEvent

class SportEventRepository(
//    val httpClient: HttpClient
) {

    // TODO #12 Connect with backend
//    suspend fun getProfile(): Profile {
//        val profile = httpClient
//            .get("http://$HOST:$PORT/profiles/1")
//            .body<List<Task>>()
//        return profile
//    }

    suspend fun getAll(): List<SportEvent> {
        return getFakeSportEvents()
    }
}