package com.spoonofcode.dojopro

import android.content.Context
import com.spoonofcode.dojopro.app.di.appModule
import com.spoonofcode.dojopro.core.settings.di.encryptedSettingsModule
import com.spoonofcode.dojopro.core.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            androidLogger()
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