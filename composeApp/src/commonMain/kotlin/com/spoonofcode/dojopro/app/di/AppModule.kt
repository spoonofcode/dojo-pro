package com.spoonofcode.dojopro.app.di

import com.spoonofcode.dojopro.core.data.di.dataModule
import com.spoonofcode.dojopro.core.domain.di.domainModule
import com.spoonofcode.dojopro.core.network.di.networkModule
import com.spoonofcode.dojopro.core.settings.di.settingsModule
import com.spoonofcode.dojopro.core.ui.di.uiModule
import com.spoonofcode.dojopro.feature.calendar.di.calendarModule
import com.spoonofcode.dojopro.feature.demo.di.demoModule
import com.spoonofcode.dojopro.feature.home.di.homeModule
import com.spoonofcode.dojopro.feature.login.di.loginModule
import com.spoonofcode.dojopro.feature.profile.di.profileModule
import com.spoonofcode.dojopro.feature.search.di.searchModule
import com.spoonofcode.dojopro.feature.shop.di.shopModule
import com.spoonofcode.dojopro.feature.sportevent.di.sportEventModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val appModule = module {
    includes(
        // core
        dataModule,
        domainModule,
        uiModule,
        networkModule,

        // features
        calendarModule,
        demoModule,
        homeModule,
        loginModule,
        profileModule,
        searchModule,
        settingsModule,
        shopModule,
        sportEventModule,
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