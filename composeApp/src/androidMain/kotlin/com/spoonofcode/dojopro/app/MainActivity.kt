package com.spoonofcode.dojopro.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.spoonofcode.dojopro.core.settings.appContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        appContext = this

        checkDataFromNotification()

        installSplashScreen()
        setContent {
            App()
//            AppTheme {
//                Navigator(DemoScreen()) { navigator ->
//                    SlideTransition(navigator = navigator)
//                }
//            }
        }
    }

    private fun requestNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val hasPermission =
                checkSelfPermission(
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED

            if (hasPermission.not()) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
            }

        }
    }

    private fun checkDataFromNotification() {
        // We can get data from notifaction like below
//        val screen = intent.getStringExtra("screen")
//        val itemId = intent.getStringExtra("item_id")
    }
}