package com.spoonofcode.dojopro.feature.login.di

import com.spoonofcode.dojopro.core.ui.utils.GoogleAuthProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformLoginModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}