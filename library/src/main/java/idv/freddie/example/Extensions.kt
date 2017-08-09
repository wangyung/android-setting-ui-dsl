package idv.freddie.example

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import org.jetbrains.anko.internals.AnkoInternals

/**
 * use ripple effect, if not, the background would be reset
 */
var View.useRippleEffect: Boolean
    get() = AnkoInternals.noGetter()
    set(value) {
        if (value) {
            background =
                    ContextCompat.getDrawable(context, context.getResIdFromAttr(R.attr.selectableItemBackground))
        } else {
            background = null
        }
    }

fun Context.getResIdFromAttr(attr: Int): Int {
    val value = TypedValue()
    theme.resolveAttribute(attr, value, true)
    return value.resourceId
}
