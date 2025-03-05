package com.spoonofcode.dojopro.feature.login.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.app.MainHostScreen
import com.spoonofcode.dojopro.core.domain.LoginGoogleUseCase
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.dojopro.feature.login.register.RegisterScreen
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
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
                loginUseCase(
                    email = viewState.value.email,
                    password = viewState.value.password,
                )
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }

    fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                loginGoogleUseCase(googleIdToken)
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
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