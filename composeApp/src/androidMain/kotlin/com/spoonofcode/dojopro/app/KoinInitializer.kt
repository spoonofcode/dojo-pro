package com.spoonofcode.dojopro.app

import android.content.Context
import com.spoonofcode.dojopro.app.di.appModule
import com.spoonofcode.dojopro.feature.chat.di.chatModule
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
                chatModule,
            )
        }
    }
}