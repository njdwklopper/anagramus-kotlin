package tech.klopper.anagramus.core.service.login

import android.app.Activity
import android.content.Intent
import tech.klopper.anagramus.core._base.google.GoogleServiceInterface
import tech.klopper.anagramus.core._base.logger.BaseLogger
import tech.klopper.anagramus.core._base.session.GoogleSignInHandler
import tech.klopper.anagramus.core._base.session.GoogleSigninViewHandler

private const val RC_SIGN_IN = 9001

class LoginHandler(
    val view: LoginViewHandler,
    val context: Activity,
    google: GoogleServiceInterface,
    log: BaseLogger
) : GoogleSigninViewHandler {

    private val googleSignInHandler = GoogleSignInHandler(
        google,
        this,
        context,
        log
    )

    fun startLogin() {
        context.startActivityForResult(
            googleSignInHandler.signIn(),
            RC_SIGN_IN
        )
    }

    fun handleActivityResult(requestCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            googleSignInHandler.handleSignInIntent(data!!)
        }
    }

    override fun handleSuccessfulLogin() {
        view.handleSuccessfulLogin()
    }

    override fun handleError(e: Exception) {
        view.handleError(e)
    }
}

interface LoginViewHandler {
    fun handleSuccessfulLogin()
    fun handleError(e: Exception)
}
