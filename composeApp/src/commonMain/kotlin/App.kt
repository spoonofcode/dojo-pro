import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import tabs.login.LoginScreen
import tabs.mainhost.MainHostScreen

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
