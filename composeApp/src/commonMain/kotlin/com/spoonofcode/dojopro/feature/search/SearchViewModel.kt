package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsByTextUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val getFilteredSportEventsUseCase: GetFilteredSportEventsUseCase,
    private val getFilteredSportEventsByTextUseCase: GetFilteredSportEventsByTextUseCase,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val filteredSportEvents = getFilteredSportEventsUseCase()
            updateState {
                copy(
                    filteredSportEvents = filteredSportEvents,
                )
            }
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val sportEvents = currentState().filteredSportEvents
            val filteredSportEvents = getFilteredSportEventsByTextUseCase(
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
            copy(isLoadingView = isLoading)
        }
    }
}