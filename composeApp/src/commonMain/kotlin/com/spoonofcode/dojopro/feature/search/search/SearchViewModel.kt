package com.spoonofcode.dojopro.feature.search.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsByTextUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredSportEventsUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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
            try {
                val filteredSportEvents = getFilteredSportEventsUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initSportEvents = filteredSportEvents,
                        filteredSportEvents = filteredSportEvents,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
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

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}