import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import tabs.login.LoginScreen

@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(LoginScreen())
    }
}

