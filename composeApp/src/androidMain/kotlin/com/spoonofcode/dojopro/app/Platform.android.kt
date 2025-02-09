package com.spoonofcode.dojopro.app

import android.os.Build
import com.spoonofcode.dojopro.app.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()