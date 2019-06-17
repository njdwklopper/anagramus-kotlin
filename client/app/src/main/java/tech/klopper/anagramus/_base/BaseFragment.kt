package tech.klopper.anagramus._base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main._base_activity.*
import org.jetbrains.anko.support.v4.ctx
import tech.klopper.anagramus.core._base.logger.BaseLogger
import tech.klopper.anagramus.core._base.logger.Logger

abstract class BaseFragment : Fragment() {
    protected lateinit var log: BaseLogger

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        log = Logger(ctx)
        log.e("ASDFGSDFGSDFGSDFGSDFGSDFGSDFHKSDKFLDFLGKSDFLKG")
        return getUIView(ctx)
    }

    abstract fun getUIView(ctx: Context): View

    protected fun showProgress(isVis: Boolean) {
        view_content.visibility = if (isVis) View.GONE else View.VISIBLE
        view_progress.visibility = if (isVis) View.VISIBLE else View.GONE
    }
}
