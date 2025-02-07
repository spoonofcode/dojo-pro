package feature.home

import androidx.lifecycle.viewModelScope
import core.base.ui.BaseViewModel
import core.base.ui.ext.launchWithProgress
import core.data.repository.SportEventRepository

internal class HomeViewModel(
    private val sportEventRepository: SportEventRepository,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEvents = sportEventRepository.readAll()
            updateState {
                copy(
                    sportEvents = sportEvents
                )
            }
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }

}