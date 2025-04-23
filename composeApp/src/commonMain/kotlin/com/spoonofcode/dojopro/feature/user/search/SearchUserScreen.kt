package com.spoonofcode.dojopro.feature.user.search

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
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.search
import com.spoonofcode.dojopro.resources.search_user
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SearchUserScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.search_user,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<SearchUserViewModel, SearchUserViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SearchUserViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SearchUserViewModel,
        viewState: SearchUserViewState
    ): @Composable ColumnScope.() -> Unit {

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel.changeSearchText(it) },
            selectUser = { viewModel.selectUser(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchUserViewState,
        changeSearchText: (String) -> Unit,
        selectUser: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            LazyColumn {
                items(viewState.filteredUsers) { user ->
                    UserEventItem(
                        item = user,
                        onClick = { selectUser(user.id) }
                    )
                }
            }
        }
    }

    @Composable
    fun UserEventItem(
        item: User,
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
                    text = item.email,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Text(
                    text = item.fullName,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}