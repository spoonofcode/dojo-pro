package repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.Profile

class ProfileRepository(
//    val httpClient: HttpClient
) {

    // TODO #12 Connect with backend
//    suspend fun getProfile(): Profile {
//        val profile = httpClient
//            .get("http://$HOST:$PORT/profiles/1")
//            .body<List<Task>>()
//        return profile
//    }

    suspend fun getProfile(): Profile {
        return Profile(
            id = 1,
            firstName = "Christiano",
            lastName = "Ronaldo",
        )
    }
}