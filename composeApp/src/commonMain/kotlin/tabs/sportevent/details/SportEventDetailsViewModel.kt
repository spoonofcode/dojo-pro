package tabs.sportevent.details

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import repository.ProfileRepository
import repository.SportEventRepository

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