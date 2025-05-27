package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.clubRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.filterRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.levelRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.loginGoogleRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.loginRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.profileRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.registerRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.roleRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.roomRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.sportEventRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.typeRepositoryMock
import com.spoonofcode.dojopro.core.data.repository.userRepositoryMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataTestModule = module {
    singleOf(::clubRepositoryMock)
    singleOf(::filterRepositoryMock)
    singleOf(::levelRepositoryMock)
    singleOf(::loginRepositoryMock)
    singleOf(::loginGoogleRepositoryMock)
    singleOf(::profileRepositoryMock)
    singleOf(::registerRepositoryMock)
    singleOf(::roleRepositoryMock)
    singleOf(::roomRepositoryMock)
    singleOf(::sportEventRepositoryMock)
    singleOf(::userRepositoryMock)
    singleOf(::typeRepositoryMock)
}