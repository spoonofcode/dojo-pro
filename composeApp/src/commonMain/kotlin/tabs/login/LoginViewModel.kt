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

internal class LoginViewModel(
    private val googleAuthRepository: GoogleAuthRepository,
    private val sessionRepository: SessionRepository,
) : BaseViewModel<LoginViewState>(LoginViewState()) {

//    fun initView() {
//        viewModelScope.launchWithProgress(
//            onProgress = ::setLoadingView
//        ) {
//            val sportEvents = sportEventRepository.readAll()
//            updateState {
//                copy(
//                    sportEvents = sportEvents
//                )
//            }
//        }
//    }

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
                // TODO #29 Store token in EncryptedDataStore preferences
                sessionRepository.saveSessionToken(token = googleAuthToken.idToken)
                updateState {
                    copy(receivedToken = googleAuthToken.idToken)
                }
            }
        }
    }

    fun getTokenFromStore() {
        val sessionToken = sessionRepository.getSessionToken()
        updateState {
            copy(receivedTokenFromStore = sessionToken ?: "TEST-STORE")
        }

    }

}