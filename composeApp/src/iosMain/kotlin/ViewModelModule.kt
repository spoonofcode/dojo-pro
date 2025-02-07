import org.koin.core.module.dsl.singleOf
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
    singleOf(::CalendarViewModel)
    singleOf(::HomeViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::SearchViewModel)
    singleOf(::ShopViewModel)

    singleOf(::SportEventCreateViewModel)
    singleOf(::SportEventDetailsViewModel)
    singleOf(::SettingsViewModel)

    singleOf(::LoginViewModel)
    singleOf(::RegisterViewModel)
    singleOf(::ForgotPasswordViewModel)
}