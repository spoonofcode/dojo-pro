package com.spoonofcode.dojopro.core.ui.utils

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging

actual class FirebaseMessageTokenProvider() {
    actual fun getFirebaseMessageToken(): String {
        return Firebase.messaging.token.result
    }
}