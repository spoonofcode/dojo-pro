package com.spoonofcode.dojopro.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.spoonofcode.dojopro.feature.calendar.CalendarTab
import com.spoonofcode.dojopro.feature.home.HomeTab
import com.spoonofcode.dojopro.feature.profile.ProfileTab
import com.spoonofcode.dojopro.feature.search.SearchTab
import com.spoonofcode.dojopro.feature.shop.ShopTab

class MainHostScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTab
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(SearchTab)
                        TabNavigationItem(CalendarTab)
                        TabNavigationItem(ShopTab)
                        TabNavigationItem(ProfileTab)
                    }
                },
                content = { CurrentTab() },
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator: TabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                tab.options.icon?.let { icon ->
                    Icon(
                        painter = icon,
                        contentDescription =
                        tab.options.title
                    )
                }
            },
            label = {
                Text(
                    text = tab.options.title
                )
            }
        )
    }
}