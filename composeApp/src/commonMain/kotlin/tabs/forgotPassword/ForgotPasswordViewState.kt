package tabs.forgotPassword

internal data class ForgotPasswordViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "",
)