package com.spoonofcode.dojopro.core.data.di

import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.FilterRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.data.repository.LoginRepository
import com.spoonofcode.dojopro.core.data.repository.MessageFCMRepository
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.data.repository.RefreshRepository
import com.spoonofcode.dojopro.core.data.repository.RegisterRepository
import com.spoonofcode.dojopro.core.data.repository.RoleRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.data.repository.TypeRepository
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::ClubRepository)
    singleOf(::LevelRepository)
    singleOf(::RoomRepository)
    singleOf(::SportEventRepository)
    singleOf(::LoginGoogleRepository)
    singleOf(::LoginRepository)
    singleOf(::RefreshRepository)
    singleOf(::UserRepository)
    singleOf(::RegisterRepository)
    singleOf(::ProfileRepository)
    singleOf(::RoleRepository)
    singleOf(::FilterRepository)
    singleOf(::TypeRepository)
    singleOf(::MessageFCMRepository)
}