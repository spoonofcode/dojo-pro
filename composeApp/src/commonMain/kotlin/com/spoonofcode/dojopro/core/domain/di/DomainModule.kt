package com.spoonofcode.dojopro.core.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.domain.RefreshUseCase

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::RefreshUseCase)
}