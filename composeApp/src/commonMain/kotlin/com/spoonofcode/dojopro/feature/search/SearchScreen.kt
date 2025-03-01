package com.spoonofcode.dojopro.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.core.ext.formatedLocalDateTime
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.filter
import org.jetbrains.compose.resources.stringResource

class SearchScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<SearchViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        // Search text state
        var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
        // Selected filter
        var selectedFilter = viewState.filterData

        // Filter the list based on searchQuery and selectedFilter
        val filteredSportEvents = remember(searchQuery.text, viewState.filterData) {
            viewState.sportEvents.filter { sportEvent ->
                val matchesSearch = sportEvent.title.contains(searchQuery.text, ignoreCase = true)
                val matchesCoachFilter = sportEvent.coach.id == selectedFilter.selectedCoachId
                val matchesLevelFilter = sportEvent.coach.id == selectedFilter.selectedLevelId
                matchesSearch && matchesCoachFilter && matchesLevelFilter
            }
        }

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Search") },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { newValue -> searchQuery = newValue },
                    label = { Text("Search sport events...") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacers.VerticalBetweenFields()

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.filter),
                    onClick = { viewModel.navigateToFilter() }
                )

                LazyColumn {
                    items(filteredSportEvents) { sportEvent ->
                        SportEventItem(
                            item = sportEvent,
                            onClick = { viewModel.selectSportEvent(sportEvent.id) }
                        )
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