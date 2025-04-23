package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsByTextUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
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
        viewModelScope.launch {
            showLoadingView()
            val filteredSportEvents = getFilteredSportEventsUseCase()
            updateState {
                copy(
                    isLoadingView = false,
                    initSportEvents = filteredSportEvents,
                    filteredSportEvents = filteredSportEvents,
                )
            }
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val sportEvents = currentState().initSportEvents
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

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }
}