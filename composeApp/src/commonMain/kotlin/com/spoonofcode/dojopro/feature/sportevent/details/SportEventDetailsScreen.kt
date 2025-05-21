package com.spoonofcode.dojopro.feature.sportevent.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.core.model.Types
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.date
import com.spoonofcode.dojopro.resources.delete
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.dojo_advanced_group_training
import com.spoonofcode.dojopro.resources.dojo_beginners_group_training
import com.spoonofcode.dojopro.resources.dojo_beginners_training
import com.spoonofcode.dojopro.resources.dojo_boxing_training
import com.spoonofcode.dojopro.resources.dojo_grappling_training
import com.spoonofcode.dojopro.resources.dojo_individual_training
import com.spoonofcode.dojopro.resources.dojo_mat_training
import com.spoonofcode.dojopro.resources.dojo_motor_training
import com.spoonofcode.dojopro.resources.dojo_open_training
import com.spoonofcode.dojopro.resources.dojo_training
import com.spoonofcode.dojopro.resources.dojo_youth_training
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
import org.koin.compose.viewmodel.koinViewModel

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

        super.reloadScreen = { viewModel.initView(sportEventId = sportEventId) }

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
            SportEventImage(viewState.sportEvent!!.type)

            Texts.BLB(viewState.sportEvent!!.title)

            SportEventItemRowElement(
                label = Res.string.date,
                elementValue = viewState.sportEvent.formatRangeWithDurationInMinutes(),
                icon = Icons.Default.Timer,
                contentDescription = "Date and time",
            )

            SportEventItemRowElement(
                label = Res.string.location,
                elementValue = viewState.sportEvent.club.name,
                icon = Icons.Default.LocationOn,
                contentDescription = "Location",
            )

            SportEventItemRowElement(
                label = Res.string.room,
                elementValue = viewState.sportEvent.room.name,
                icon = Icons.Default.MeetingRoom,
                contentDescription = "Room",
            )

            SportEventItemRowElement(
                label = Res.string.coach,
                elementValue = viewState.sportEvent.creatorUser.fullName,
                icon = Icons.Default.Person,
                contentDescription = "Coach",
            )

            SportEventItemRowElement(
                label = Res.string.level,
                elementValue = viewState.sportEvent.level.name,
                icon = Icons.Default.Leaderboard,
                contentDescription = "Level",
            )

            SportEventItemRowElement(
                label = Res.string.type,
                elementValue = viewState.sportEvent.type.name,
                icon = Icons.Default.SportsMartialArts,
                contentDescription = "Type",
            )

            SportEventItemRowElement(
                label = Res.string.cost,
                elementValue = viewState.sportEvent.cost.toString(),
                icon = Icons.Default.Paid,
                contentDescription = "Cost",
            )

            SportEventItemRowElement(
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
    private fun SportEventImage(type: Type) {
        val imageTrainingType = when (type.id) {
            Types.DOJO_BOXING_TRAINING.id -> Res.drawable.dojo_boxing_training
            Types.DOJO_GRAPPLING_TRAINING.id -> Res.drawable.dojo_grappling_training
            Types.DOJO_YOUTH_TRAINING.id -> Res.drawable.dojo_youth_training
            Types.DOJO_BEGINNERS_GROUP_TRAINING.id -> Res.drawable.dojo_beginners_group_training
            Types.DOJO_BEGINNERS_TRAINING.id -> Res.drawable.dojo_beginners_training
            Types.DOJO_INDIVIDUAL_TRAINING.id -> Res.drawable.dojo_individual_training
            Types.DOJO_MOTOR_TRAINING.id -> Res.drawable.dojo_motor_training
            Types.DOJO_OPEN_TRAINING.id -> Res.drawable.dojo_open_training
            Types.DOJO_MAT_TRAINING.id -> Res.drawable.dojo_mat_training
            Types.DOJO_ADVANCED_GROUP_TRAINING.id -> Res.drawable.dojo_advanced_group_training
            else -> Res.drawable.dojo_training
        }

        Image(
            painter = painterResource(resource = imageTrainingType),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacers.VerticalBetweenFields()
    }

    @Composable
    private fun SportEventItemRowElement(
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