package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.LoginGoogle

object LoginGoogleMockData {
    val LOGIN_GOOGLE_1 = LoginGoogle(
        userId = 1,
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}