package com.spoonofcode.dojopro

import android.app.Application

class DojoProApp : Application() {

    override fun onCreate() {
        super.onCreate()
        com.spoonofcode.dojopro.KoinInitializer(applicationContext).init()
    }
}