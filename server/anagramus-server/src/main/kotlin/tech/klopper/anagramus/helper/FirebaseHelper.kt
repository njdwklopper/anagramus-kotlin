package tech.klopper.anagramus.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Component

@Component
class FirebaseHelper : FirebaseAuthLoader {
    override fun verifyIdToken(idToken: String): FirebaseToken {
        return FirebaseAuth.getInstance().verifyIdToken(idToken)
    }
}

interface FirebaseAuthLoader {
    fun verifyIdToken(idToken: String): FirebaseToken
}
