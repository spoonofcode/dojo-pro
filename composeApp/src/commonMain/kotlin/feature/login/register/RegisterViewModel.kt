package feature.login.register

import core.network.SessionRepository
import androidx.lifecycle.viewModelScope
import core.base.ui.BaseViewModel
import core.base.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import core.model.RegisterRequest
import core.data.repository.RegisterRepository
import app.MainHostScreen

internal class RegisterViewModel(
    private val registerRepository: RegisterRepository,
    private val sessionRepository: SessionRepository,
) : BaseViewModel<RegisterViewState>(RegisterViewState()) {

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

    fun changeFirstName(firstName: String) {
        viewModelScope.launch {
            updateState {
                copy(firstName = firstName)
            }
        }
    }

    fun changeLastName(lastName: String) {
        viewModelScope.launch {
            updateState {
                copy(lastName = lastName)
            }
        }
    }

    fun signUp() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                withContext(Dispatchers.IO) {
                    registerRepository.create(
                        RegisterRequest(
                            email = viewState.value.email,
                            password = viewState.value.password,
                            firstName = viewState.value.firstName,
                            lastName = viewState.value.lastName,
                        )
                    )
                }
            }.onSuccess { token ->
                runCatching {
                    withContext(Dispatchers.IO) {
                        sessionRepository.saveSessionAccessToken(token = token.jwtAccessToken)
                    }
                }.onSuccess {
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }
            }
        }
    }
}