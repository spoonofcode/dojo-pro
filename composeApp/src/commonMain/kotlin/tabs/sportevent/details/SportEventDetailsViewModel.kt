package tabs.sportevent.details

import core.ui.BaseViewModel
import repository.ProfileRepository

internal class SportEventDetailsViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<SportEventDetailsViewState>(SportEventDetailsViewState()) {

//    fun confirmEvent() {
//        viewModelScope.launch {
//            val profile = profileRepository.getProfile()
//            updateState {
//                copy(
//                    profile = profile
//                )
//            }
//        }
//    }

    fun changeTitle(title:String) {

    }

}