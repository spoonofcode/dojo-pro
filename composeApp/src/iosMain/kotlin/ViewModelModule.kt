import org.koin.core.module.dsl.singleOf
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
    singleOf(::CalendarViewModel)
    singleOf(::HomeViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::SearchViewModel)
    singleOf(::ShopViewModel)

    singleOf(::SportEventCreateViewModel)
    singleOf(::SportEventDetailsViewModel)
    singleOf(::SettingsViewModel)

    singleOf(::LoginViewModel)
    singleOf(::RegistrationViewModel)
    singleOf(::ForgotPasswordViewModel)
}