package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.CoachRepository
import repository.LevelRepository
import repository.ProfileRepository
import repository.UserRepository
import repository.RoomRepository
import repository.SportEventRepository
import repository.LoginGoogleRepository
import repository.LoginRepository
import repository.RegisterRepository
import navigation.ViewModelNavigator
import SessionRepository

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
    singleOf(::LoginGoogleRepository)
    singleOf(::SessionRepository)
    singleOf(::UserRepository)
    singleOf(::LoginRepository)
    singleOf(::RegisterRepository)

    singleOf(::ViewModelNavigator)
}