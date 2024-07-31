package tabs.sportevent

import model.SportEvent

internal data class CreateSportEventViewState(
    val title: String = "Profile title",
    val sportEvent: SportEvent? = null,
)