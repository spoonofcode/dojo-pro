package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.CoachRepository
import repository.LevelRepository
import repository.ProfileRepository
import repository.RoomRepository
import repository.SportEventRepository

val appModule = module {
    singleOf(::ProfileRepository)
    singleOf(::CoachRepository)
    singleOf(::LevelRepository)
    singleOf(::RoomRepository)
    singleOf(::SportEventRepository)
}