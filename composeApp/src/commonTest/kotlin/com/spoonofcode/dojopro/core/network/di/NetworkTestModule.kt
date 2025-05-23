package com.spoonofcode.dojopro.core.network.di

import com.spoonofcode.dojopro.core.network.networkManagerMock
import com.spoonofcode.dojopro.core.network.sessionManagerMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkTestModule = module {
    singleOf(::sessionManagerMock)
    singleOf(::networkManagerMock)
}