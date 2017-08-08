package idv.freddie.example.sdsl

import android.content.Context
import android.view.ViewManager
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._ScrollView
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class Settings(ctx: Context) : _ScrollView(ctx) {
    internal val rootContainer = verticalLayout().lparams(width = matchParent, height = wrapContent)
    val groups: MutableList<SettingGroup> = mutableListOf()

    fun addGroup(group: SettingGroup) {
        groups.add(group)
        rootContainer.addView(group)
    }
}

fun ViewManager.settings(init: (@AnkoViewDslMarker Settings).() -> Unit): Settings {
    val ctx = AnkoInternals.wrapContextIfNeeded(AnkoInternals.getContext(this), 0)
    val settings = Settings(ctx)
    settings.init()
    AnkoInternals.addView(this, settings)
    return settings
}
