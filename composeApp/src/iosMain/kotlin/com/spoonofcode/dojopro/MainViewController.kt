package com.spoonofcode.dojopro

import androidx.compose.ui.window.ComposeUIViewController
import com.spoonofcode.dojopro.app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }