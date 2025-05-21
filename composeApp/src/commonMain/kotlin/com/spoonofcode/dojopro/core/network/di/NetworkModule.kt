package com.spoonofcode.dojopro.core.network.di

import com.spoonofcode.dojopro.core.network.NetworkManager
import com.spoonofcode.dojopro.core.network.SessionManager
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformNetworkModule: Module

val networkModule: Module = module {
    includes(platformNetworkModule)
    singleOf(::SessionManager)
    singleOf(::NetworkManager)
}