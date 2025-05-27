package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.Login

object LoginMockData {
    val LOGIN_1 = Login(
        userId = 1,
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}