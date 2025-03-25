package com.spoonofcode.dojopro.feature.sportevent.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.ext.formatedLocalDateTime
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.delete
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.dojo_room
import com.spoonofcode.dojopro.resources.edit
import com.spoonofcode.dojopro.resources.end
import com.spoonofcode.dojopro.resources.join_to_sport_event
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.room
import com.spoonofcode.dojopro.resources.sport_event
import com.spoonofcode.dojopro.resources.start
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class SportEventDetailsScreen(
    val sportEventId: Int,
    override val screenTopAppBarTitle: StringResource = Res.string.sport_event,
    override val backNavigationEnable: Boolean = false,
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

        return ContentView(
            viewState = viewState,
            editSportEvent = { viewModel.editSportEvent() },
            deleteSportEvent = { viewModel.deleteSportEvent() },
            joinToSportEvent = { viewModel.joinToSportEvent() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SportEventDetailsViewState,
        editSportEvent: () -> Unit,
        deleteSportEvent: () -> Unit,
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

            Texts.HS(viewState.sportEvent!!.title)
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.start))
            Texts.BL(viewState.sportEvent.startDateTime.formatedLocalDateTime())
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.end))
            Texts.BL(viewState.sportEvent.endDateTime.formatedLocalDateTime())
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.description))
            Texts.BL(viewState.sportEvent.description)
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.cost))
            Texts.BL(viewState.sportEvent.cost)
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.coach))
            Texts.BL(viewState.sportEvent.coach.fullName)
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.room))
            Texts.BL(viewState.sportEvent.room.name)
            Spacers.VerticalBetweenFields()

            Texts.HS(stringResource(Res.string.level))
            Texts.BL(viewState.sportEvent.level.name)
            Spacers.VerticalBetweenFields()

            Spacers.Weight1(this)

            Spacers.VerticalBetweenFields()
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.edit),
                onClick = editSportEvent
            )

            Spacers.VerticalBetweenFields()
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.delete),
                onClick = deleteSportEvent
            )

            Spacers.VerticalBetweenFields()
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.join_to_sport_event),
                onClick = joinToSportEvent
            )
        }
    }
}