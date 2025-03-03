package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.domain.GetFilterDataUseCase
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val sportEventRepository: SportEventRepository,
    private val getFilterDataUseCase: GetFilterDataUseCase,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val filterData = getFilterDataUseCase()

            val sportEvents = sportEventRepository.readAll()

            val filteredSportEvents = getFilteredSportEvents(
                sportEvents = sportEvents,
                selectedFilter = filterData
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
            val filteredSportEvents = currentState().allSportEvents.filter { sportEvent ->
                sportEvent.title.contains(searchText, ignoreCase = true)
            }
            println("BARTEK #changeSearchText filteredSportEvents: $filteredSportEvents")
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

    private fun getFilteredSportEvents(
        sportEvents: List<SportEvent>,
        selectedFilter: FilterData
    ): List<SportEvent> {
        return sportEvents.filter { sportEvent ->
            val matchesCoachFilter = selectedFilter.selectedCoachId == null ||
                    selectedFilter.selectedCoachId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.coach.id == selectedFilter.selectedCoachId
            val matchesLevelFilter = selectedFilter.selectedLevelId == null ||
                    selectedFilter.selectedLevelId == FilterViewState.ALL_OPTION_ID ||
                    sportEvent.level.id == selectedFilter.selectedLevelId
            matchesCoachFilter && matchesLevelFilter
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }
}