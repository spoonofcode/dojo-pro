package com.spoonofcode.dojopro.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.core.ui.Dimens
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.feature.settings.SettingsScreen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_room
import com.spoonofcode.dojopro.resources.events_created_by_me
import com.spoonofcode.dojopro.resources.events_i_participated_in
import com.spoonofcode.dojopro.resources.settings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()
        val viewState by viewModel.viewState.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.initView()
        }
        ContentView(
            viewState = viewState,
            navigateToSettings = { navigator.push(SettingsScreen()) }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: ProfileViewState,
        navigateToSettings: () -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profile") },
                )
            }
        ) { innerPadding ->
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.screenPadding)
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        RoundedProfileImage()
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Texts.HS(viewState.profile!!.firstName)
                    Spacers.VerticalBetweenFields()

                    Texts.HS(stringResource(Res.string.events_created_by_me))
                    Texts.BL(viewState.profile.numberOfEventsCreatedByUser.toString())
                    Spacers.VerticalBetweenFields()

                    Texts.HS(stringResource(Res.string.events_i_participated_in))
                    Texts.BL(viewState.profile.numberOfEventsUserParticipatedIn.toString())
                    Spacers.VerticalBetweenFields()

                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.settings),
                        onClick = navigateToSettings
                    )

                    Spacers.BottomSpace()
                }
            }

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
}

// region Previews
@Composable
private fun PreviewContentView(viewState: ProfileViewState = ProfileViewState()) {
    ProfileScreen().ContentView(
        viewState,
        navigateToSettings = {},
    )
}


@Composable
@Preview
private fun ProfileScreenPreview() {
    PreviewContentView()
}
// endregion