package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.CoachRepository
import repository.LevelRepository
import repository.ProfileRepository
import repository.RoomRepository
import repository.SportEventRepository
import repository.GoogleAuthRepository

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }
    singleOf(::ProfileRepository)
    singleOf(::CoachRepository)
    singleOf(::LevelRepository)
    singleOf(::RoomRepository)
    singleOf(::SportEventRepository)
    singleOf(::GoogleAuthRepository)
}