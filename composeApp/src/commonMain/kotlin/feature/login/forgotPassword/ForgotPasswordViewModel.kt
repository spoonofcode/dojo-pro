package feature.login.forgotPassword

import core.network.SessionRepository
import androidx.lifecycle.viewModelScope
import core.base.ui.BaseViewModel
import core.base.ui.ext.launchWithProgress
import kotlinx.coroutines.launch
import core.data.repository.LoginGoogleRepository

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