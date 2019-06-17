package tech.klopper.anagramus._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main._base_activity.*
import org.jetbrains.anko.startActivity
import tech.klopper.anagramus.R
import tech.klopper.anagramus.app.home.HomeActivity
import tech.klopper.anagramus.app.login.LoginActivity
import tech.klopper.anagramus.core._base.logger.BaseLogger
import tech.klopper.anagramus.core._base.logger.Logger
import tech.klopper.anagramus.core._base.session.InitSessionHandle
import tech.klopper.anagramus.core._base.session.InitSessionHandler
import tech.klopper.anagramus.core._base.session.SessionViewHandler

abstract class BaseActivity : AppCompatActivity(), SessionViewHandler {

    protected lateinit var log: BaseLogger
    private lateinit var session: InitSessionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout._base_activity)

        log = Logger(this)
        session = InitSessionHandle(
            this,
            log
        )
    }

    protected fun handleAppInitSession() {
        session.handleAppInitSession()
    }

    override fun setContentView(id: Int) {
        val linearLayout = findViewById<LinearLayout>(R.id.view_content)
        val view = LayoutInflater.from(baseContext).inflate(id, null)
        linearLayout.addView(view)
    }

    override fun handleGotoLoginView() {
        startActivity<LoginActivity>()
        finish()
    }

    override fun handleGotoHomeView() {
        startActivity<HomeActivity>()
        finish()
    }

    protected fun showProgress(isVis: Boolean) {
        view_content.visibility = if (isVis) View.GONE else View.VISIBLE
        view_progress.visibility = if (isVis) View.VISIBLE else View.GONE
    }

    public override fun onStop() {
        super.onStop()
        showProgress(false)
    }
}
