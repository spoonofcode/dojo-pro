package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.ProfileRepositoryBartek
import com.spoonofcode.dojopro.core.data.repository.mockdata.profileRepositoryMock
import org.koin.dsl.module

val dataTestModule = module {
    single<ProfileRepositoryBartek> { profileRepositoryMock }
}