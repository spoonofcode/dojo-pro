package com.spoonofcode.dojopro.core.network.di

import org.koin.dsl.bind
import org.koin.dsl.module
import com.spoonofcode.dojopro.core.network.NetworkConfig
import com.spoonofcode.dojopro.core.network.SessionManager
import org.koin.core.module.dsl.singleOf

data class NetworkConfigAndroid(
    override val host: String = "10.0.2.2",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val networkModule = module {
    singleOf(::SessionManager)
    single { NetworkConfigAndroid() } bind NetworkConfig::class
}

