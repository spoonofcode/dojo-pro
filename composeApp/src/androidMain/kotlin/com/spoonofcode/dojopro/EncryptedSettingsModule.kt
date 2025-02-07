package com.spoonofcode.dojopro

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val encryptedSettingsModule = module {
    singleOf(::createEncryptedSettings)
}