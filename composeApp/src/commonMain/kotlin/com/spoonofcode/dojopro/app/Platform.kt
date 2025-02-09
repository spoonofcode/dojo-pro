package com.spoonofcode.dojopro.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform