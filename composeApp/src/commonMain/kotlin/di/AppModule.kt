package di

import core.network.SessionRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import core.navigation.ViewModelNavigator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import core.data.repository.CoachRepository
import core.data.repository.LevelRepository
import core.data.repository.LoginGoogleRepository
import core.data.repository.LoginRepository
import core.data.repository.ProfileRepository
import core.data.repository.RegisterRepository
import core.data.repository.RoomRepository
import core.data.repository.SportEventRepository
import core.data.repository.UserRepository
import core.domain.LoginUseCase
import core.domain.TokenUseCase

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