package com.spoonofcode.dojopro.core.settings.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformSettingsModule: Module

val settingsModule: Module = module {
    includes(platformSettingsModule)
}