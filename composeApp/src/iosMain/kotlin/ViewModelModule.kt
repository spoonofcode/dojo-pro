import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import tabs.calendar.CalendarViewModel
import tabs.home.HomeViewModel
import tabs.profile.ProfileViewModel
import tabs.search.SearchViewModel
import tabs.shop.ShopViewModel
import tabs.sportevent.create.SportEventCreateViewModel
import tabs.settings.SettingsViewModel

actual val viewModelModule = module {
    singleOf(::CalendarViewModel)
    singleOf(::HomeViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::SearchViewModel)
    singleOf(::ShopViewModel)

    singleOf(::SportEventCreateViewModel)
    singleOf(::SettingsViewModel)
}