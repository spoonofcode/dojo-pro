package feature.home

import core.model.SportEvent

internal data class HomeViewState(
    val isViewEnable:Boolean = true,
    val isViewLoading:Boolean = true,
    val sportEvents: List<SportEvent> = emptyList(),
)