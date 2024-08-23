package tabs.sportevent.create

import model.Coach
import model.Level
import model.Room
import model.SportEvent

internal data class SportEventCreateViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val title: String = "Profile title",
    val sportEvent: SportEvent? = null,


    val coaches: List<Coach> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val levels: List<Level> = emptyList(),

    val eventTitle: String? = null,
    val eventDescription: String? = null,
    val selectedCoach: Coach? = null,
    val selectedRoomId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedMinNumberOfPeople: Int? = null,
    val selectedMaxNumberOfPeople: Int? = null,
    val cost: String? = null,

)