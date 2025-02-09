package com.spoonofcode.dojopro.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository

internal class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<ProfileViewState>(ProfileViewState()) {

    fun updateTasks() {
        viewModelScope.launch {
            val profile = profileRepository.getProfile()
            updateState {
                copy(
                    profile = profile
                )
            }
        }
    }

}