package tabs.login

internal data class LoginViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
)