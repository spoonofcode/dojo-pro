package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.profileRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.sportEventRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.userRepositoryMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataTestModule = module {
    singleOf(::profileRepositoryMock)
    singleOf(::sportEventRepositoryMock)
    singleOf(::userRepositoryMock)
}