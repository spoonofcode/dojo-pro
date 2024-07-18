package com.spoonofcode.dojopro

import KoinInitializer
import android.app.Application

class DojoProApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}