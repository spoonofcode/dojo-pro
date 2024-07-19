package tabs.profile

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import kotlinx.coroutines.launch
import repository.ProfileRepository

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