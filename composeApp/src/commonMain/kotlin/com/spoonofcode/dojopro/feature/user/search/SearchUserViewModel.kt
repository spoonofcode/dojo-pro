package com.spoonofcode.dojopro.feature.user.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetAllUsersUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredUsersByTextUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch

internal class SearchUserViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getFilteredUsersByTextUseCase: GetFilteredUsersByTextUseCase,
) : BaseViewModel<SearchUserViewState>(SearchUserViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val users = getAllUsersUseCase()
            updateState {
                copy(
                    filteredUsers = users,
                )
            }
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val users = currentState().filteredUsers
            val filteredUsers = getFilteredUsersByTextUseCase(
                searchText = searchText,
                users = users,
            )
            updateState {
                copy(searchText = searchText, filteredUsers = filteredUsers)
            }
        }
    }

    fun selectUser(userId: Int) {
        viewModelScope.launch {
//            viewModelNavigator.push(UserDetailScreen(userId = userId))
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}