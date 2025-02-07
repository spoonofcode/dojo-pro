package com.spoonofcode.dojopro.app

import com.spoonofcode.dojopro.core.network.SessionRepository
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.spoonofcode.dojopro.core.base.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import com.spoonofcode.dojopro.feature.login.login.LoginScreen

@Composable
@Preview
fun App() {
    val sessionRepository: SessionRepository = getKoin().get()

    AppTheme {
        Navigator(getStartScreen(sessionRepository.isSessionInitialized()))
    }
}

@Composable
private fun getStartScreen(sessionInitialized: Boolean): Screen {
    return if (sessionInitialized) {
        MainHostScreen()
    } else {
        LoginScreen()
    }

}
