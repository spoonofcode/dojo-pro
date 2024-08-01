package tabs.sportevent.create

import core.ui.BaseViewModel
import repository.ProfileRepository

internal class CreateSportEventViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<CreateSportEventViewState>(CreateSportEventViewState()) {

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