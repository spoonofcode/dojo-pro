package com.spoonofcode.dojopro.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.ext.formatedLocalDate
import com.spoonofcode.dojopro.core.model.Levels
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.dojopro.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.filter
import com.spoonofcode.dojopro.resources.search
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SearchScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.search,
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<SearchViewModel, SearchViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SearchViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SearchViewModel,
        viewState: SearchViewState
    ): @Composable ColumnScope.() -> Unit {
        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.FilterList,
                description = stringResource(resource = Res.string.filter),
                onClick = { viewModel.navigateToFilter() }
            ),
        )

        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel.changeSearchText(it) },
            selectSportEvent = { viewModel.selectSportEvent(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchViewState,
        changeSearchText: (String) -> Unit,
        selectSportEvent: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            val entries = remember(viewState.filteredSportEvents) {
                buildEntries(viewState.filteredSportEvents)
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                entries.forEach { entry ->
                    when (entry) {
                        is ListEntry.DateHeader -> stickyHeader {
                            DateSeparator(entry.date)
                        }

                        is ListEntry.EventRow -> item(key = entry.event.id) {
                            SportEventItem(
                                item = entry.event,
                                onClick = { selectSportEvent(entry.event.id) }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SportEventItem(
        item: SportEvent,
        onClick: () -> Unit,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column {
                LevelBar(item)

                Column(
                    modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
                ) {
                    Texts.BLB(
                        text = item.title,
                    )
                    SportEventItemRowElement(
                        elementValue = item.formatRangeWithDurationInMinutes(),
                        icon = Icons.Default.Timer,
                        contentDescription = "Date and time",
                    )

                    SportEventItemRowElement(
                        elementValue = item.club.name,
                        icon = Icons.Default.LocationOn,
                        contentDescription = "Location",
                    )

                    SportEventItemRowElement(
                        elementValue = item.creatorUser.fullName,
                        icon = Icons.Default.Person,
                        contentDescription = "Coach",
                    )
                }
            }
        }
    }

    @Composable
    private fun LevelBar(item: SportEvent) {
        val levelColor = when (item.level.id) {
            Levels.BEGINNER.id -> Color.Yellow
            Levels.BASIC.id -> Color.Green
            Levels.ADVANCE.id -> Color.Blue
            Levels.PRO.id -> Color.Red
            else -> Color.Gray
        }

        Box(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(levelColor)
        )
    }

    private sealed interface ListEntry {
        data class DateHeader(val date: LocalDate) : ListEntry
        data class EventRow(val event: SportEvent) : ListEntry
    }

    private fun buildEntries(events: List<SportEvent>): List<ListEntry> =
        events
            .sortedBy { it.startDateTime }
            .groupBy { it.startDateTime.date }
            .flatMap { (date, sameDayEvents) ->
                listOf(ListEntry.DateHeader(date)) +
                        sameDayEvents.map { ListEntry.EventRow(it) }
            }

    @Composable
    private fun DateSeparator(date: LocalDate) {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Texts.HSB(
                text = date.formatedLocalDate(),
            )
        }
    }

    @Composable
    private fun SportEventItemRowElement(
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
            Texts.BM(
                text = elementValue,
            )
        }
    }
}