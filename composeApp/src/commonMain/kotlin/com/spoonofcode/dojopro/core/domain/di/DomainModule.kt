package com.spoonofcode.dojopro.core.domain.di

import com.spoonofcode.dojopro.core.domain.AddRoleToUserUseCase
import com.spoonofcode.dojopro.core.domain.AddUserToSportEventUseCase
import com.spoonofcode.dojopro.core.domain.CreateSportEventUseCase
import com.spoonofcode.dojopro.core.domain.DeleteSportEventUseCase
import com.spoonofcode.dojopro.core.domain.EditSportEventUseCase
import com.spoonofcode.dojopro.core.domain.GetAllSportEventsUseCase
import com.spoonofcode.dojopro.core.domain.GetAllUsersUseCase
import com.spoonofcode.dojopro.core.domain.GetFilterDataUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsByTextUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredUsersByTextUseCase
import com.spoonofcode.dojopro.core.domain.GetProfileUseCase
import com.spoonofcode.dojopro.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventByIdUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.domain.GetUserByIdUseCase
import com.spoonofcode.dojopro.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFilterFormDataUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFormDataUseCase
import com.spoonofcode.dojopro.core.domain.LoginGoogleUseCase
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import com.spoonofcode.dojopro.core.domain.SetFilterDataUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::LoginGoogleUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::RefreshAccessTokenUseCase)
    singleOf(::GetProfileUseCase)
    singleOf(::GetRolesByUserIdUseCase)
    singleOf(::AddUserToSportEventUseCase)
    singleOf(::CreateSportEventUseCase)
    singleOf(::DeleteSportEventUseCase)
    singleOf(::EditSportEventUseCase)
    singleOf(::GetSportEventByIdUseCase)
    singleOf(::LoadSportEventFormDataUseCase)
    singleOf(::LoadSportEventFilterFormDataUseCase)
    singleOf(::GetAllSportEventsUseCase)
    singleOf(::GetSportEventsUserParticipatedInUseCase)
    singleOf(::GetSportEventsCreatedByUserUseCase)
    singleOf(::SetFilterDataUseCase)
    singleOf(::GetFilterDataUseCase)
    singleOf(::GetFilteredSportEventsUseCase)
    singleOf(::GetFilteredSportEventsByTextUseCase)
    singleOf(::GetFilteredUsersByTextUseCase)
    singleOf(::GetAllUsersUseCase)
    singleOf(::AddRoleToUserUseCase)
    singleOf(::GetUserByIdUseCase)
    singleOf(::HasSpecialSettingsUseCase)
}