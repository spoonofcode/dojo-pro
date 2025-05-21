package com.spoonofcode.dojopro.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.RoundedImage
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_room
import com.spoonofcode.dojopro.resources.events_created_by_me
import com.spoonofcode.dojopro.resources.events_i_participated_in
import com.spoonofcode.dojopro.resources.profile
import com.spoonofcode.dojopro.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

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

        super.reloadScreen = { viewModel.initView() }

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
                RoundedImage(imageRes = Res.drawable.dojo_room)
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