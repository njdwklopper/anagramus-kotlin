import android.content.Context
import android.view.View
import android.view.ViewManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent

fun ViewManager.insertFragment(fragment: Fragment, activity: FragmentActivity) = linearLayout {
    id = View.generateViewId()
    lparams(width = matchParent, height = matchParent)
    activity.supportFragmentManager.beginTransaction().add(
        id,
        fragment
    ).commit()
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}