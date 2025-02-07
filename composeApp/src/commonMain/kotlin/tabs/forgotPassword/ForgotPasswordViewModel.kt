package tabs.forgotPassword

import SessionRepository
import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch
import repository.LoginGoogleRepository

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionRepository: SessionRepository,
) : BaseViewModel<ForgotPasswordViewState>(ForgotPasswordViewState()) {

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(email = email)
            }
        }
    }

    fun resetPassword() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }
}