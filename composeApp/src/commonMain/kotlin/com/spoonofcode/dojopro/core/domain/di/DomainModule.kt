package com.spoonofcode.dojopro.core.domain.di

import com.spoonofcode.dojopro.core.domain.AddRoleToUserUseCase
import com.spoonofcode.dojopro.core.domain.AddUserToSportEventUseCase
import com.spoonofcode.dojopro.core.domain.CreateSportEventUseCase
import com.spoonofcode.dojopro.core.domain.DeleteAccountUseCase
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
import com.spoonofcode.dojopro.core.domain.LogoutUseCase
import com.spoonofcode.dojopro.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import com.spoonofcode.dojopro.core.domain.SendMessageFCMUseCase
import com.spoonofcode.dojopro.core.domain.SetFilterDataUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LoginGoogleUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::RefreshAccessTokenUseCase)
    factoryOf(::GetProfileUseCase)
    factoryOf(::GetRolesByUserIdUseCase)
    factoryOf(::AddUserToSportEventUseCase)
    factoryOf(::CreateSportEventUseCase)
    factoryOf(::DeleteSportEventUseCase)
    factoryOf(::EditSportEventUseCase)
    factoryOf(::GetSportEventByIdUseCase)
    factoryOf(::LoadSportEventFormDataUseCase)
    factoryOf(::LoadSportEventFilterFormDataUseCase)
    factoryOf(::GetAllSportEventsUseCase)
    factoryOf(::GetSportEventsUserParticipatedInUseCase)
    factoryOf(::GetSportEventsCreatedByUserUseCase)
    factoryOf(::SetFilterDataUseCase)
    factoryOf(::GetFilterDataUseCase)
    factoryOf(::GetFilteredSportEventsUseCase)
    factoryOf(::GetFilteredSportEventsByTextUseCase)
    factoryOf(::GetFilteredUsersByTextUseCase)
    factoryOf(::GetAllUsersUseCase)
    factoryOf(::AddRoleToUserUseCase)
    factoryOf(::GetUserByIdUseCase)
    factoryOf(::HasSpecialSettingsUseCase)
    factoryOf(::SendMessageFCMUseCase)
}