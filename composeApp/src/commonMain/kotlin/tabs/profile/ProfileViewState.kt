package tabs.profile

import model.Profile

internal data class ProfileViewState(
    val title: String = "Profile title",
    val profile: Profile? = null,
)