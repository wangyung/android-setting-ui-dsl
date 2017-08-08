/*
 * Copyright (c) 2017 LINE Corporation. All rights Reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package idv.freddie.example.sdsl

import android.content.Context
import android.widget.LinearLayout
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.wrapContent

class SettingGroup(ctx: Context): _LinearLayout(ctx) {
    var topSeperatorHeight = 0
    var bottomSeperatorHeight = 0
    init {
        orientation = LinearLayout.VERTICAL
    }
}

fun Settings.settingGroup(init: (@AnkoViewDslMarker SettingGroup).() -> Unit): SettingGroup {
   val settingGroup = SettingGroup(this.context)
    settingGroup.init()
    settingGroup.layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
        topMargin = settingGroup.topSeperatorHeight
        bottomMargin = settingGroup.bottomSeperatorHeight
    }
    this.addGroup(settingGroup)
    return settingGroup
}
