package tabs.search

import model.SportEvent

internal data class SearchViewState(
    val sportEvents: List<SportEvent> = emptyList(),
)