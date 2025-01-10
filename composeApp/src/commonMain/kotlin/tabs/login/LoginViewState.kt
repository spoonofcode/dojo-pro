package tabs.login

internal data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
)