package com.spoonofcode.dojopro.core.domain.di

import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.domain.ProfileUseCase
import com.spoonofcode.dojopro.core.domain.RefreshUseCase
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::RefreshUseCase)
    singleOf(::ProfileUseCase)
}