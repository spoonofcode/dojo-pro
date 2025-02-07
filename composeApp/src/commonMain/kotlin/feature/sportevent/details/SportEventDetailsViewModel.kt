package feature.sportevent.details

import androidx.lifecycle.viewModelScope
import core.base.ui.BaseViewModel
import core.data.repository.SportEventRepository
import core.base.ui.ext.launchWithProgress

internal class SportEventDetailsViewModel(
    private val sportEventRepository: SportEventRepository
) : BaseViewModel<SportEventDetailsViewState>(SportEventDetailsViewState()) {

    fun initView(sportEventId: Int) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEvent = sportEventRepository.read(id = sportEventId)
            updateState {
                copy(
                    sportEvent = sportEvent
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