package tech.klopper.anagramus.core._base.session

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import tech.klopper.anagramus.core._base.google.GoogleServiceInterface
import tech.klopper.anagramus.core._base.logger.BaseLogger

class GoogleSignInHandler(
    google: GoogleServiceInterface,
    private val viewHandler: GoogleSigninViewHandler,
    private val context: Activity,
    private val log: BaseLogger
) {
    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(google.getWebID())
        .requestEmail()
        .build()

    private val googleSignInClient: GoogleSignInClient =
        GoogleSignIn.getClient(context, googleSignInOptions)

    private fun signIn(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        val firebaseHandler = FirebaseUserHandler(log)
        firebaseHandler.getAuth().signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                try {
                    if (task.isSuccessful) {
                        log.d("signInWithCredential:success")
                        viewHandler.handleSuccessfulLogin()
                    } else {
                        log.e("signInWithCredential:failure", task.exception!!)
                        throw Exception(task.exception)
                    }
                } catch (e: Exception) {
                    viewHandler.handleError(e)
                }
            }
    }

    fun handleSignInIntent(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)
            signIn(account!!)
        } catch (e: ApiException) {
            log.e("Google sign in failed", e)
            viewHandler.handleError(e)
        }
    }

    fun signIn(): Intent {
        return googleSignInClient.signInIntent
    }

//  TODO: setup login/out flow

//    fun signOut() {
//        firebaseHandler.signOut()
//        googleSignInClient.signOut().addOnCompleteListener(context) {
//            //            updateUI(null)
//        }
//    }
//
//    fun revokeAccess() {
//        firebaseHandler.signOut()
//        googleSignInClient.revokeAccess().addOnCompleteListener(context) {
//            //            updateUI(null)
//        }
//    }
}

interface GoogleSigninViewHandler {
    fun handleSuccessfulLogin()
    fun handleError(e: Exception)
}
