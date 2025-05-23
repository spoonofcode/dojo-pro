package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.profileRepositoryMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataTestModule = module {
    singleOf(::profileRepositoryMock)
}