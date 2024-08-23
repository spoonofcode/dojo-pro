package tabs.sportevent.create

internal data class SportEventCreateViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = true,
    val title: String = "",
    val description: String = "",

    val coaches: Map<Int, String> = emptyMap(),
    val rooms: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),

    val eventTitle: String? = null,
    val eventDescription: String? = null,
    val selectedCoachId: Int? = null,
    val selectedRoomId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedMinNumberOfPeople: Int = DEFAULT_MIN_NUMBER_OF_PEOPLE,
    val selectedMaxNumberOfPeople: Int = DEFAULT_MAX_NUMBER_OF_PEOPLE,

    val cost: String = "",
) {
    companion object {
        private const val DEFAULT_MIN_NUMBER_OF_PEOPLE = 4
        private const val DEFAULT_MAX_NUMBER_OF_PEOPLE = 8
    }
}