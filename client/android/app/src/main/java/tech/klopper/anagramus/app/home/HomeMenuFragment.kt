package tech.klopper.anagramus.app.home

import android.content.Context
import android.view.Gravity
import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import tech.klopper.anagramus.R
import tech.klopper.anagramus._base.BaseFragment
import tech.klopper.anagramus.core._base.session.FirebaseUserHandler
import tech.klopper.anagramus.core.service.home.HomeHandle
import tech.klopper.anagramus.core.service.home.HomeHandler
import tech.klopper.anagramus.core.service.home.HomeViewHandler

class HomeMenuFragment : BaseFragment(), HomeViewHandler {
    override fun getUIView(ctx: Context): View {
        return HomeFragmentUi().createView(
            AnkoContext.create(
                ctx,
                HomeHandler(
                    this,
                    FirebaseUserHandler(log),
                    log
                )
            )
        )
    }

    override fun handleResponse(message: String) {
        Snackbar.make(this.view!!, "Button says: $message", Snackbar.LENGTH_SHORT).show()
    }
}

class HomeFragmentUi : AnkoComponent<HomeHandle> {
    override fun createView(ui: AnkoContext<HomeHandle>): View {
        return with(ui) {
            verticalLayout {
                lparams(width = matchParent)
                gravity = Gravity.CENTER_HORIZONTAL
                themedButton(R.string.who_am_i, R.style.Widget_MaterialComponents_Button) {
                    onClick {
                        ui.owner.pressButton()
                    }
                }.lparams {
                    topMargin = dip(80)
                }
            }
        }
    }
}
