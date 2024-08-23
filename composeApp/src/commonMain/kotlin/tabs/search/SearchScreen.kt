package tabs.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.ui.ext.koinViewModel
import model.SportEvent
import tabs.sportevent.details.SportEventDetailsScreen

class SearchScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SearchViewModel>()
        val viewState by viewModel.viewState.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.getSportEvents()
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
                    .padding(16.dp)
                    .padding(innerPadding)
            ) {
                viewState.sportEvents.let {
                    LazyColumn(
                        content = {
                            itemsIndexed(it) { index: Int, item: SportEvent ->
                                SportEventItem(
                                    item = item,
                                    onClick = { navigator.push(SportEventDetailsScreen(sportEventId = 1)) }
                                )
                            }
                        }
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
                    text = item.creationDate.toString(),
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