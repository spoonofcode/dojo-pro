package com.spoonofcode.dojopro.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.dojopro.core.ext.formatedLocalDateTime
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.filter
import com.spoonofcode.dojopro.resources.search
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
        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel.changeSearchText(it) },
            navigateToFilter = { viewModel.navigateToFilter() },
            selectSportEvent = { viewModel.selectSportEvent(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchViewState,
        changeSearchText: (String) -> Unit,
        navigateToFilter: () -> Unit,
        selectSportEvent: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.filter),
                onClick = { navigateToFilter() }
            )

            LazyColumn {
                items(viewState.filteredSportEvents) { sportEvent ->
                    SportEventItem(
                        item = sportEvent,
                        onClick = { selectSportEvent(sportEvent.id) }
                    )
                }
            }
        }
    }

    @Composable
    fun SportEventItem(
        item: SportEvent,
        onClick: () -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.creationDate.formatedLocalDateTime(),
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Text(
                    text = item.description,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }

}