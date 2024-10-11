package tabs.login

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import repository.SportEventRepository

internal class LoginViewModel(
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

}