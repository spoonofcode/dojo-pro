package tabs.login

import SessionRepository
import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.LoginGoogleRequest
import model.LoginRequest
import repository.LoginGoogleRepository
import repository.LoginRepository
import tabs.forgotPassword.ForgotPasswordScreen
import tabs.mainhost.MainHostScreen
import tabs.register.RegisterScreen

internal class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val loginGoogleRepository: LoginGoogleRepository,
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
            viewModelNavigator.push(ForgotPasswordScreen())
        }
    }

    fun signIn() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                withContext(Dispatchers.IO) {
                    loginRepository.create(
                        LoginRequest(
                            email = viewState.value.email,
                            password = viewState.value.password,
                        )
                    )
                }
            }.onSuccess { token ->
                runCatching {
                    withContext(Dispatchers.IO) {
                        sessionRepository.saveSessionToken(token = token.idToken)
                    }
                }.onSuccess {
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }
            }
        }

    }

    fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                withContext(Dispatchers.IO) {
                    loginGoogleRepository.create(
                        request = LoginGoogleRequest(
                            googleIdToken = googleIdToken,
                        )
                    )
                }
            }.onSuccess { token ->
                runCatching {
                    withContext(Dispatchers.IO) {
                        sessionRepository.saveSessionToken(token = token.idToken)
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
            viewModelNavigator.push(RegisterScreen())
        }
    }
}