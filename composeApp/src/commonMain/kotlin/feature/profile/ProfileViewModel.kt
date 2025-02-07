package feature.profile

import androidx.lifecycle.viewModelScope
import core.base.ui.BaseViewModel
import kotlinx.coroutines.launch
import core.data.repository.ProfileRepository

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