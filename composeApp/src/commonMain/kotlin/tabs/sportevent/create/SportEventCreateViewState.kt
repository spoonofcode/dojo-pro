package tabs.sportevent.create

import fakeData.getFakeCoaches
import model.Coach
import model.SportEvent

internal data class SportEventCreateViewState(
    val title: String = "Profile title",
    val sportEvent: SportEvent? = null,
    val coaches: List<Coach> = getFakeCoaches(),
)