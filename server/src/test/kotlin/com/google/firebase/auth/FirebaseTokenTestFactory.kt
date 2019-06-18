package com.google.firebase.auth

import tech.klopper.anagramus.helper.FirebaseAuthLoader

class FirebaseTokenTestFactory(private val claims: Map<String, Any>): FirebaseAuthLoader {
    override fun verifyIdToken(idToken: String): FirebaseToken {
        return FirebaseToken(claims)
    }
}
