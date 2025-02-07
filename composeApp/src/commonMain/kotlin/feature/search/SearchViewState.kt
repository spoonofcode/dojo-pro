package feature.search

import core.model.SportEvent

internal data class SearchViewState(
    val sportEvents: List<SportEvent> = emptyList(),
)