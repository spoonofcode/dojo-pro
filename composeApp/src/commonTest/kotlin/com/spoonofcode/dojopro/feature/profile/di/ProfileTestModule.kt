package com.spoonofcode.dojopro.feature.profile.di

import com.spoonofcode.dojopro.core.data.di.dataTestModule
import com.spoonofcode.dojopro.core.domain.di.domainModule
import com.spoonofcode.dojopro.core.network.di.networkTestModule
import com.spoonofcode.dojopro.core.settings.di.settingsModule
import com.spoonofcode.dojopro.core.ui.di.uiModule
import org.koin.dsl.module

val profileTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        dataTestModule,
        domainModule,
        profileModule,
        uiModule,
    )
}