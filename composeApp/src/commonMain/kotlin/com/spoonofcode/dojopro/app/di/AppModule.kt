package com.spoonofcode.dojopro.app.di

import com.spoonofcode.dojopro.authModule
import com.spoonofcode.dojopro.core.data.di.dataModule
import com.spoonofcode.dojopro.core.domain.di.domainModule
import com.spoonofcode.dojopro.core.network.di.networkModule
import com.spoonofcode.dojopro.core.settings.di.settingsModule
import com.spoonofcode.dojopro.core.ui.di.uiModule
import com.spoonofcode.dojopro.viewModelModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        domainModule,
        uiModule,
        viewModelModule,
        networkModule,
        authModule,
        settingsModule,
    )
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
}