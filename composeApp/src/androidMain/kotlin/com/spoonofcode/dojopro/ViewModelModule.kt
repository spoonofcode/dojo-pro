import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import feature.calendar.CalendarViewModel
import feature.login.forgotPassword.ForgotPasswordViewModel
import feature.home.HomeViewModel
import feature.login.login.LoginViewModel
import feature.profile.ProfileViewModel
import feature.login.register.RegisterViewModel
import feature.search.SearchViewModel
import feature.settings.SettingsViewModel
import feature.shop.ShopViewModel
import feature.sportevent.create.SportEventCreateViewModel
import feature.sportevent.details.SportEventDetailsViewModel

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
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}