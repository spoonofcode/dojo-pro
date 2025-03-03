package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEvents
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsByText
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val sportEventRepository: SportEventRepository,
    private val getFilteredSportEvents: GetFilteredSportEvents,
    private val getFilteredSportEventsByText: GetFilteredSportEventsByText,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEvents = sportEventRepository.readAll()
            val filteredSportEvents = getFilteredSportEvents(
                sportEvents = sportEvents,
            )
            updateState {
                copy(
                    allSportEvents = sportEvents,
                    filteredSportEvents = filteredSportEvents,
                )
            }
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val sportEvents = currentState().allSportEvents
            val filteredSportEvents = getFilteredSportEventsByText(
                searchText = searchText,
                sportEvents = sportEvents,
            )
            updateState {
                copy(searchText = searchText, filteredSportEvents = filteredSportEvents)
            }
        }
    }

    fun selectSportEvent(sportEventId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(SportEventDetailsScreen(sportEventId = sportEventId))
        }
    }

    fun navigateToFilter() {
        viewModelScope.launch {
            viewModelNavigator.push(FilterScreen())
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }
}