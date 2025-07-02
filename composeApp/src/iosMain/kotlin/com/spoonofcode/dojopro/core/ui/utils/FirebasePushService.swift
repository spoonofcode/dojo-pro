package com.spoonofcode.dojopro.core.ui.utils

import Foundation
import FirebaseMessaging

@objc class FirebasePushServiceObjC: NSObject {
    @objc static func getToken(completion: @escaping (String?) -> Void) {
        Messaging.messaging().token { token, error in
            if let error = error {
                print("Error getting FCM token: \(error)")
                completion(nil)
            } else {
                completion(token)
            }
        }
    }

    @objc static func subscribe(topic: String) {
        Messaging.messaging().subscribe(toTopic: topic)
    }

    @objc static func unsubscribe(topic: String) {
        Messaging.messaging().unsubscribe(fromTopic: topic)
    }
}