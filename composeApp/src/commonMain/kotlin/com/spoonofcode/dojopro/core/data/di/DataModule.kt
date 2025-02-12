package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.data.repository.LoginRepository
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.data.repository.RegisterRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CoachRepository)
    singleOf(::LevelRepository)
    singleOf(::RoomRepository)
    singleOf(::SportEventRepository)
    singleOf(::LoginGoogleRepository)
    singleOf(::LoginRepository)
    singleOf(::UserRepository)
    singleOf(::RegisterRepository)
    singleOf(::ProfileRepository)
}