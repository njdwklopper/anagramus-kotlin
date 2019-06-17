package tech.klopper.anagramus.app.home

import android.os.Build
import android.os.Bundle
import android.view.View
import insertFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout
import tech.klopper.anagramus.R
import tech.klopper.anagramus._base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeUI().setContentView(this)
    }
}

class HomeUI : AnkoComponent<BaseActivity> {
    override fun createView(ui: AnkoContext<BaseActivity>): View {
        return with(ui) {
            coordinatorLayout {
                lparams(width = matchParent, height = matchParent)
                themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                    lparams(width = matchParent, height = wrapContent)
                    toolbar {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            popupTheme = R.style.AppTheme_PopupOverlay
                        }
                    }
                }
                insertFragment(HomeMenuFragment(), owner)
            }
        }
    }
}
