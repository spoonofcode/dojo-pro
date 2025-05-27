package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.Register

object RegisterMockData {
    val REGISTER_1 = Register(
        userId = 1,
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}