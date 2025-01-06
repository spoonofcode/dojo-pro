package tabs.login

import SessionRepository
import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import model.GoogleAuthTokenRequest
import repository.GoogleAuthRepository
import tabs.mainhost.MainHostScreen

internal class LoginViewModel(
    private val googleAuthRepository: GoogleAuthRepository,
    private val sessionRepository: SessionRepository,
) : BaseViewModel<LoginViewState>(LoginViewState()) {

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }

    fun sendGoogleToken(idToken: String) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                withContext(Dispatchers.IO) {
                    googleAuthRepository.create(
                        request = GoogleAuthTokenRequest(
                            idToken = idToken,
                        )
                    )
                }
            }.onSuccess { googleAuthToken ->
                runCatching {
                    withContext(Dispatchers.IO) {
                        sessionRepository.saveSessionToken(token = googleAuthToken.idToken)
                    }
                }.onSuccess {
                    updateState {
                        copy(receivedToken = googleAuthToken.idToken)
                    }
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }
            }
        }
    }
}