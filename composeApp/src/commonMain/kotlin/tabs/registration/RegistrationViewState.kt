package tabs.registration

internal data class RegistrationViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
)