/*
 * Copyright (c) 2017 LINE Corporation. All rights Reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package idv.freddie.example.sdsl

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import idv.freddie.example.R
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.sp
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalMargin
import org.jetbrains.anko.wrapContent

class SettingGroup(ctx: Context): _LinearLayout(ctx) {
    var topSeperatorHeight = 0
    var bottomSeperatorHeight = 0
    var title: String
        set(value) { titleView.text = value }
        get() = titleView.text.toString()

    private var titleView: TextView
    init {
        orientation = LinearLayout.VERTICAL
        titleView = textView {
            textColor = ctx.resources.getColor(R.color.grey_454545)
            textSize = sp(4).toFloat()
        }.lparams(width = matchParent, height = wrapContent) {
            verticalMargin = dip(4)
            leftMargin = dip(12)
        }
    }
}

inline fun Settings.settingGroup(init: (@AnkoViewDslMarker SettingGroup).() -> Unit): SettingGroup {
    val settingGroup = SettingGroup(this.context)
    settingGroup.init()
    settingGroup.layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
        topMargin = settingGroup.topSeperatorHeight
        bottomMargin = settingGroup.bottomSeperatorHeight
    }
    this.addGroup(settingGroup)
    return settingGroup
}
