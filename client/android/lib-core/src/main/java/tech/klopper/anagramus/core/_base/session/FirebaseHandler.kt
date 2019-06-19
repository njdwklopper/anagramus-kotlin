package tech.klopper.anagramus.core._base.session

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import tech.klopper.anagramus.core._base.logger.BaseLogger

class FirebaseUserHandler(private val log: BaseLogger) :
    FirebaseHandler {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isUserNotNull(): Boolean {
        return auth.currentUser != null
    }

    override fun getAuth(): FirebaseAuth {
        return auth
    }

    override fun getUser(): FirebaseUser {
        return auth.currentUser!!
    }

    override fun getToken(): String? {
        //Todo thread off
        var token: String? = null
        getUser().getIdToken(true).addOnCompleteListener {
            if (it.isSuccessful) {
                token = it.result!!.token
                log.e(token!!)
            } else {
                log.e("ERROR", it.exception!!)
            }
        }
        return token
    }

    override fun signOut() {
        auth.signOut()
    }
}

interface FirebaseHandler {
    fun isUserNotNull(): Boolean
    fun getAuth(): FirebaseAuth
    fun getUser(): FirebaseUser
    fun getToken(): String?
    fun signOut()
}
