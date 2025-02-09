package com.spoonofcode.dojopro.core.network

import com.russhwolf.settings.Settings

class SessionManager(private val settings: Settings) {
    fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    fun saveSessionAccessToken(token: String) {
        settings.putString(SESSION_ACCESS_TOKEN, token)
    }

    fun getSessionAccessToken(): String? {
        return settings.getStringOrNull(SESSION_ACCESS_TOKEN)
    }

    fun clearSessionAccessToken() {
        settings.remove(SESSION_ACCESS_TOKEN)
    }

    fun saveSessionRefreshToken(token: String) {
        settings.putString(SESSION_REFRESH_TOKEN, token)
    }

    fun getSessionRefreshToken(): String? {
        return settings.getStringOrNull(SESSION_REFRESH_TOKEN)
    }

    fun clearSessionRefreshToken() {
        settings.remove(SESSION_REFRESH_TOKEN)
    }

    companion object {
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
    }
}