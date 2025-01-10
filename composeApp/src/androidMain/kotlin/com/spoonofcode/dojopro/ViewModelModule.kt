import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import tabs.calendar.CalendarViewModel
import tabs.forgotPassword.ForgotPasswordViewModel
import tabs.home.HomeViewModel
import tabs.login.LoginViewModel
import tabs.profile.ProfileViewModel
import tabs.registration.RegistrationViewModel
import tabs.search.SearchViewModel
import tabs.settings.SettingsViewModel
import tabs.shop.ShopViewModel
import tabs.sportevent.create.SportEventCreateViewModel
import tabs.sportevent.details.SportEventDetailsViewModel

actual val viewModelModule = module {
    viewModelOf(::CalendarViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ShopViewModel)

    viewModelOf(::SportEventCreateViewModel)
    viewModelOf(::SportEventDetailsViewModel)
    viewModelOf(::SettingsViewModel)

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}