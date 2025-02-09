package com.spoonofcode.dojopro.app

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }