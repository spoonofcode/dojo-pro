package tabs.sportevent.create

import fakeData.getFakeCoaches
import model.Coach
import model.SportEvent

internal data class CreateSportEventViewState(
    val title: String = "Profile title",
    val sportEvent: SportEvent? = null,
    val coaches: List<Coach> = getFakeCoaches(),
)