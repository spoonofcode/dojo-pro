package com.spoonofcode.dojopro.core.ui.utils

import androidx.compose.runtime.ProvidableCompositionLocal

interface ClipboardManager {
    fun setText(text: String)
}

expect val LocalClipboardManager: ProvidableCompositionLocal<ClipboardManager?>