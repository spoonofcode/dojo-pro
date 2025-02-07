package com.spoonofcode.dojopro.app.di

import com.spoonofcode.dojopro.core.network.SessionRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import com.spoonofcode.dojopro.core.navigation.ViewModelNavigator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.data.repository.LoginRepository
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.data.repository.RegisterRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.domain.TokenUseCase

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