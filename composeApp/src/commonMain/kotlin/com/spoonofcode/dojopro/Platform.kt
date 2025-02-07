package com.spoonofcode.dojopro

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform