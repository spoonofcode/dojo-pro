package tabs.login

import SessionRepository
import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
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

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(email = email)
            }
        }
    }

    fun changePassword(password: String) {
        viewModelScope.launch {
            updateState {
                copy(password = password)
            }
        }
    }

    fun forgotPassword() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }

    fun signIn() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }

    fun signInWithGoogle(idToken: String) {
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
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }
            }
        }
    }

    fun signUp() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }
}