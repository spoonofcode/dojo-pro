package com.spoonofcode.dojopro

import com.spoonofcode.dojopro.app.App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.dojopro.core.ui.theme.AppTheme
import com.spoonofcode.dojopro.core.settings.appContext

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this

        // TODO #17 Switch language from settings screen
//        this.applicationContext.setLocale(Locale("pl"))

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppTheme {
        App()
    }
}