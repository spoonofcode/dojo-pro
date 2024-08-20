package tabs.search

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import kotlinx.coroutines.launch
import repository.SportEventRepository

internal class SearchViewModel(
    private val sportEventRepository: SportEventRepository,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    fun getSportEvents() {
        viewModelScope.launch {
            val sportEvents = sportEventRepository.getAll()
            updateState {
                copy(
                    sportEvents = sportEvents
                )
            }
        }
    }

}