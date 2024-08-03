package tabs.sportevent.details

import fakeData.getFakeCoaches
import model.Coach
import model.SportEvent

internal data class SportEventDetailsViewState(
    val title: String = "Profile title",
    val sportEvent: SportEvent? = null,
    val coaches: List<Coach> = getFakeCoaches(),
)