package com.spoonofcode.dojopro.core.domain.di

import com.spoonofcode.dojopro.core.domain.AddUserToSportEventUseCase
import com.spoonofcode.dojopro.core.domain.CreateSportEventUseCase
import com.spoonofcode.dojopro.core.domain.DeleteSportEventUseCase
import com.spoonofcode.dojopro.core.domain.EditSportEventUseCase
import com.spoonofcode.dojopro.core.domain.GetAllSportEvents
import com.spoonofcode.dojopro.core.domain.GetProfileUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventByIdUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFormDataUseCase
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.domain.LoginWithGoogleUseCase
import com.spoonofcode.dojopro.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::LoginWithGoogleUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::RefreshAccessTokenUseCase)
    singleOf(::GetProfileUseCase)
    singleOf(::AddUserToSportEventUseCase)
    singleOf(::CreateSportEventUseCase)
    singleOf(::DeleteSportEventUseCase)
    singleOf(::EditSportEventUseCase)
    singleOf(::GetSportEventByIdUseCase)
    singleOf(::LoadSportEventFormDataUseCase)
    singleOf(::GetAllSportEvents)
    singleOf(::GetSportEventsUserParticipatedInUseCase)
    singleOf(::GetSportEventsCreatedByUserUseCase)
}