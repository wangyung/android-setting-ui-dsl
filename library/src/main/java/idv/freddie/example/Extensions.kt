package idv.freddie.example

import android.content.Context
import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import org.jetbrains.anko.AnkoException

/**
 * use ripple effect, if not, the background would be reset
 */
var View.useRippleEffect: Boolean
    get() = throw AnkoException("useRippleEffect doesn't support getter")
    set(value) {
        if (value) {
            background =
                    ContextCompat.getDrawable(context, context.getResIdFromAttr(R.attr.selectableItemBackground))
        } else {
            background = null
        }
    }

fun Context.getResIdFromAttr(attr: Int): Int {
    val ret = TypedValue()
    theme.resolveAttribute(attr, ret, true)
    return ret.resourceId
}
