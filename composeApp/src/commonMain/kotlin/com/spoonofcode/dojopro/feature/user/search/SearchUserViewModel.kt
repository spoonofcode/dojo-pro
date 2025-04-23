package com.spoonofcode.dojopro.feature.user.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetAllUsersUseCase
import com.spoonofcode.dojopro.core.domain.GetFilteredUsersByTextUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.user.details.UserDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class SearchUserViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getFilteredUsersByTextUseCase: GetFilteredUsersByTextUseCase,
) : BaseViewModel<SearchUserViewState>(SearchUserViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val users = getAllUsersUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initUsers = users,
                        filteredUsers = users,
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
            val users = currentState().initUsers
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
            viewModelNavigator.push(UserDetailsScreen(userId = userId))
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