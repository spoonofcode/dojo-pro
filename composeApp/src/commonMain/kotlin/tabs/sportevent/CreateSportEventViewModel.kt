package tabs.sportevent

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import kotlinx.coroutines.launch
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