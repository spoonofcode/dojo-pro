package tabs.register

import SessionRepository
import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.LoginGoogleRequest
import model.RegisterRequest
import repository.RegisterRepository
import tabs.mainhost.MainHostScreen

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
                        sessionRepository.saveSessionToken(token = token.idToken)
                    }
                }.onSuccess {
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }
            }
        }
    }
}