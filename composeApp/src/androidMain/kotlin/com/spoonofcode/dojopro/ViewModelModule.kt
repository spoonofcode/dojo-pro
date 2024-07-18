import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import tabs.calendar.CalendarViewModel
import tabs.home.HomeViewModel
import tabs.profile.ProfileViewModel
import tabs.search.SearchViewModel
import tabs.shop.ShopViewModel

actual val viewModelModule = module {
    viewModelOf(::CalendarViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ShopViewModel)
}