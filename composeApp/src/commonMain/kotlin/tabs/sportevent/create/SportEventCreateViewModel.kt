package tabs.sportevent.create

import core.ui.BaseViewModel
import repository.ProfileRepository

internal class SportEventCreateViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<SportEventCreateViewState>(SportEventCreateViewState()) {

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