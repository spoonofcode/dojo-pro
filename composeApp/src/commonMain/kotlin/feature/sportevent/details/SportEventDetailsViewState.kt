package feature.sportevent.details

import core.model.SportEvent

internal data class SportEventDetailsViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val sportEvent: SportEvent? = null,
)