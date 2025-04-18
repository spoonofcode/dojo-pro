package com.spoonofcode.dojopro.feature.sportevent.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.date
import com.spoonofcode.dojopro.resources.delete
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.dojo_room
import com.spoonofcode.dojopro.resources.edit
import com.spoonofcode.dojopro.resources.join_to_sport_event
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.location
import com.spoonofcode.dojopro.resources.room
import com.spoonofcode.dojopro.resources.sport_event
import com.spoonofcode.dojopro.resources.type
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class SportEventDetailsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.sport_event,
    val sportEventId: Int,
) : BaseScreen<SportEventDetailsViewModel, SportEventDetailsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SportEventDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SportEventDetailsViewModel,
        viewState: SportEventDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView(sportEventId = sportEventId)
        }

        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.Edit,
                description = stringResource(resource = Res.string.edit),
                onClick = { viewModel.editSportEvent() }
            ),
            TopBarAction(
                icon = Icons.Default.Delete,
                description = stringResource(resource = Res.string.delete),
                onClick = { viewModel.deleteSportEvent() }
            ),
        )

        return ContentView(
            viewState = viewState,
            joinToSportEvent = { viewModel.joinToSportEvent() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SportEventDetailsViewState,
        joinToSportEvent: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Image(
                painter = painterResource(resource = Res.drawable.dojo_room),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Texts.BLB(viewState.sportEvent!!.title)

            getSportEventItemRowElement(
                label = Res.string.date,
                elementValue = viewState.sportEvent.formatRangeWithDurationInMinutes(),
                icon = Icons.Default.Timer,
                contentDescription = "Date and time",
            )

            getSportEventItemRowElement(
                label = Res.string.location,
                elementValue = viewState.sportEvent.club.name,
                icon = Icons.Default.LocationOn,
                contentDescription = "Location",
            )

            getSportEventItemRowElement(
                label = Res.string.room,
                elementValue = viewState.sportEvent.room.name,
                icon = Icons.Default.MeetingRoom,
                contentDescription = "Room",
            )

            getSportEventItemRowElement(
                label = Res.string.coach,
                elementValue = viewState.sportEvent.creatorUser.fullName,
                icon = Icons.Default.Person,
                contentDescription = "Coach",
            )

            getSportEventItemRowElement(
                label = Res.string.level,
                elementValue = viewState.sportEvent.level.name,
                icon = Icons.Default.Leaderboard,
                contentDescription = "Level",
            )

            getSportEventItemRowElement(
                label = Res.string.type,
                elementValue = viewState.sportEvent.type.name,
                icon = Icons.Default.SportsMartialArts,
                contentDescription = "Type",
            )

            getSportEventItemRowElement(
                label = Res.string.cost,
                elementValue = viewState.sportEvent.cost,
                icon = Icons.Default.Paid,
                contentDescription = "Cost",
            )

            getSportEventItemRowElement(
                label = Res.string.description,
                elementValue = viewState.sportEvent.description,
                icon = Icons.Default.Description,
                contentDescription = "Description",
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.join_to_sport_event),
                onClick = joinToSportEvent
            )
        }
    }

    @Composable
    private fun getSportEventItemRowElement(
        label: StringResource,
        elementValue: String,
        icon: ImageVector,
        contentDescription: String,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
            Spacers.HorizontalBetweenFields()
            Column {
                Texts.BSB(
                    text = stringResource(resource = label),
                )
                Texts.BM(
                    text = elementValue,
                )

            }
        }
        Spacers.VerticalBetweenFields()
    }
}