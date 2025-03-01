package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.domain.GetFilterDataUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
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
            updateState {
                copy(
                    filterData = filterData,
                    sportEvents = sportEvents,
                )
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