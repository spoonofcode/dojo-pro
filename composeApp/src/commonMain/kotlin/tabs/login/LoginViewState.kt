package tabs.login

internal data class LoginViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "leo.messi@gmail.com",
    val password: String = "leo123",
)