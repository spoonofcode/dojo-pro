package com.spoonofcode.dojopro

import android.app.Application
import com.spoonofcode.dojopro.app.KoinInitializer

class DojoProApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}