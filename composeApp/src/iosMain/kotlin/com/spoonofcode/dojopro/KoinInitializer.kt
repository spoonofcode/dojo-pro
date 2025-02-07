package com.spoonofcode.dojopro

import com.spoonofcode.dojopro.app.di.appModule
import com.spoonofcode.dojopro.core.network.di.networkModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                appModule,
                viewModelModule,
                networkModule,
                authModule,
                encryptedSettingsModule,
            )
        }
    }
}