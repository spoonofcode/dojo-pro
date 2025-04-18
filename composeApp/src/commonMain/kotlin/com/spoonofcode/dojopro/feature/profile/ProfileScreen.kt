package com.spoonofcode.dojopro.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_room
import com.spoonofcode.dojopro.resources.events_created_by_me
import com.spoonofcode.dojopro.resources.events_i_participated_in
import com.spoonofcode.dojopro.resources.profile
import com.spoonofcode.dojopro.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ProfileScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.profile,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileViewModel, ProfileViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ProfileViewModel,
        viewState: ProfileViewState
    ): @Composable ColumnScope.() -> Unit {
        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.Settings,
                description = stringResource(resource = Res.string.settings),
                onClick = { viewModel.navigateToSettings() }
            ),
        )

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: ProfileViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                RoundedProfileImage()
                Spacers.VerticalBetweenFields()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Texts.HSB(viewState.profile!!.name)
                Spacers.VerticalBetweenFields()
            }

            Texts.BLB(stringResource(Res.string.events_created_by_me))
            Texts.BM(viewState.profile!!.numberOfEventsCreatedByUser.toString())
            Spacers.VerticalBetweenFields()

            Texts.BLB(stringResource(Res.string.events_i_participated_in))
            Texts.BM(viewState.profile.numberOfEventsUserParticipatedIn.toString())
        }
    }

    @Composable
    fun RoundedProfileImage(
        modifier: Modifier = Modifier,
        contentDescription: String? = null
    ) {
        // You can wrap the Image in a Surface with CircleShape
        Surface(
            modifier = modifier
                .size(120.dp), // adjust to desired size
            shape = CircleShape,
        ) {
            Image(
                painter = painterResource(resource = Res.drawable.dojo_room),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }

    // region previews
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedProfileScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = ProfileViewState(
                    profile = Profile(
                        name = "Bartosz luczak (Lycha)",
                        numberOfEventsCreatedByUser = 1,
                        numberOfEventsUserParticipatedIn = 2
                    )
                ),
            )
        )
    }
    // endregion
}