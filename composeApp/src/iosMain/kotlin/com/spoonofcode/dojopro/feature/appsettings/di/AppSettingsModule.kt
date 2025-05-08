package com.spoonofcode.dojopro.feature.appsettings.di

import com.spoonofcode.dojopro.feature.appsettings.AppSettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val appSettingsModule = module {
    singleOf(::AppSettingsViewModel)
}