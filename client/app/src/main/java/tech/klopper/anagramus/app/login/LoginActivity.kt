package tech.klopper.anagramus.app.login

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity
import tech.klopper.anagramus.R
import tech.klopper.anagramus._base.BaseActivity
import tech.klopper.anagramus.app.home.HomeActivity
import tech.klopper.anagramus.core._base.google.GoogleServiceInterface
import tech.klopper.anagramus.core.service.login.LoginHandler
import tech.klopper.anagramus.core.service.login.LoginViewHandler

class LoginActivity : BaseActivity(), LoginViewHandler,
    GoogleServiceInterface {

    private lateinit var loginHandler: LoginHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        loginHandler = LoginHandler(
            this,
            this,
            this,
            log
        )

        signInButton.setOnClickListener {
            loginHandler.startLogin()
        }
    }

    override fun getWebID(): String {
        return getString(R.string.default_web_client_id)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginHandler.handleActivityResult(requestCode, data)
    }

    override fun handleSuccessfulLogin() {
        startActivity<HomeActivity>()
        finish()
    }

    override fun handleError(e: Exception) {
        showProgress(false)
        Snackbar.make(main_layout, "Authentication Failed: ${e.message}", Snackbar.LENGTH_SHORT).show()
    }
}
