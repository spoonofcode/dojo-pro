import androidx.compose.ui.window.ComposeUIViewController
import app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }