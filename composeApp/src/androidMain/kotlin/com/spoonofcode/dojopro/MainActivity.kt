package com.spoonofcode.dojopro

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tabs.sportevent.CreateSportEventScreen
import tabs.sportevent.CreateSportEventViewState
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO #17 Switch language from settings screen
        this.applicationContext.setLocale(Locale("pl"))

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()

    CreateSportEventScreen().ContentView(
        viewState = CreateSportEventViewState(),
        changeTitle = {},
    )
}