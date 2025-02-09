package com.spoonofcode.dojopro.app

import android.app.Application

class DojoProApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}