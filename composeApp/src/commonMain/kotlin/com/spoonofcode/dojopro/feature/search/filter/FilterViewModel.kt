package com.spoonofcode.dojopro.feature.search.filter

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetFilterDataUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFilterFormDataUseCase
import com.spoonofcode.dojopro.core.domain.SetFilterDataUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState.Companion.ALL_OPTION_ID
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState.Companion.ALL_OPTION_NAME
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class FilterViewModel(
    private val loadSportEventFilterFormDataUseCase: LoadSportEventFilterFormDataUseCase,
    private val setFilterDataUseCase: SetFilterDataUseCase,
    private val getFilterDataUseCase: GetFilterDataUseCase,
) : BaseViewModel<FilterViewState>(FilterViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                loadSportEventFilterFormDataUseCase()
            }.onSuccess { sportEventFilterFormData ->
                val filterData = getFilterDataUseCase()
                val selectedCoachId = filterData.selectedCoachId ?: ALL_OPTION_ID
                val selectedLevelId = filterData.selectedLevelId ?: ALL_OPTION_ID
                updateState {
                    copy(
                        selectedCoachId = selectedCoachId,
                        selectedLevelId = selectedLevelId,
                        coaches = addAllOption().plus(sportEventFilterFormData.coaches.associate { it.id to it.fullName }),
                        levels = addAllOption().plus(sportEventFilterFormData.levels.associate { it.id to it.name }),
                    )
                }
            }
        }
    }

    private fun addAllOption(): Map<Int, String> = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME)

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }

    fun changeCoach(selectedCoachId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedCoachId = selectedCoachId)
            }
        }
    }

    fun changeLevel(selectedLevelId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedLevelId = selectedLevelId)
            }
        }
    }

    fun changeStartDateTime(startDateTime: LocalDateTime) {
        viewModelScope.launch {
//            updateState {
//                copy(startDateTime = startDateTime)
//            }
        }
    }

    fun changeEndDateTime(endDateTime: LocalDateTime) {
        viewModelScope.launch {
//            updateState {
//                copy(endDateTime = endDateTime)
//            }
        }
    }

    fun applyFilter() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val currentState = currentState()
            setFilterDataUseCase(
                selectedCoachId = currentState.selectedCoachId,
                selectedLevelId = currentState.selectedLevelId,
            )
            navigateBack()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }
}