package di

import SessionRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import navigation.ViewModelNavigator
import network.AuthPlugin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.CoachRepository
import repository.LevelRepository
import repository.LoginGoogleRepository
import repository.LoginRepository
import repository.ProfileRepository
import repository.RegisterRepository
import repository.RoomRepository
import repository.SportEventRepository
import repository.UserRepository
import tabs.login.LoginUseCase
import tabs.login.TokenUseCase

val appModule = module {
    singleOf(::TokenUseCase)
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }

            // TODO #34-Try use custom AuthPlugin instead of safeApiCall function
//            install(AuthPlugin) {
//                // Provide a suspend function that does your refresh call
//                refreshTokenFunc = {
//                    getKoin().get<TokenUseCase>().refreshAccessToken()
//                }
//            }
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
    singleOf(::LoginUseCase)

    singleOf(::ViewModelNavigator)
}