/*
 * Copyright (c) 2017 LINE Corporation. All rights Reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package idv.freddie.example.app

import android.app.Activity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewManager
import android.widget.Toolbar
import idv.freddie.example.sdsl.SettingItem
import idv.freddie.example.sdsl.Settings
import idv.freddie.example.sdsl.settingGroup
import idv.freddie.example.sdsl.settingItem
import idv.freddie.example.sdsl.settings
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.sdk25.listeners.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import org.jetbrains.anko.toolbar
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent


interface MainAction {
    fun showToast(message: String)
}

class MainActivity : Activity(), MainAction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = MainActivityUi().apply { setContentView(this@MainActivity) }
        setActionBar(ui.toolBar)
    }

    override fun showToast(message: String) {
        toast(message)
    }
}

class MainActivityUi : AnkoComponent<MainAction> {
    lateinit var toolBar: Toolbar
    private lateinit var owner: MainAction
    override fun createView(ui: AnkoContext<MainAction>) = with(ui) {
        this@MainActivityUi.owner = owner
        verticalLayout {
            themedAppBarLayout(theme = R.style.AppTheme_AppBarOverlay) {
                lparams(width = matchParent, height = wrapContent)
                toolBar = toolbar {
                    val typedValue = TypedValue()
                    ctx.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
                    val actionBarHeight =
                            TypedValue.complexToDimensionPixelSize(typedValue.data, ctx.resources.displayMetrics)
                    lparams(width = matchParent, height = actionBarHeight)
                }
            }

            setting3(this).lparams(width = matchParent, height = matchParent)
        }
    }

    @SuppressWarnings("deprecation")
    private fun setting1(viewManager: ViewManager): Settings = with(viewManager) {
        settings {
            backgroundColor = resources.getColor(android.R.color.white)
            settingGroup {
                title = "Group1"
                settingItem {
                    leftIconResId = R.drawable.icon1
                    rightIconResId = R.drawable.icon_arrow
                    title = "Item1"
                    onClick { owner.showToast("click item1") }
                }

                settingItem(descriptionType = SettingItem.DescriptionType.TEXT) {
                    leftIconResId = R.drawable.icon2
                    rightIconResId = R.drawable.icon_arrow
                    title = "Item2"
                    description = "Description2"
                    onClick { owner.showToast("click item2") }
                }

                settingItem(descriptionType = SettingItem.DescriptionType.BADGE) {
                    leftIconResId = R.drawable.icon3
                    rightIconResId = R.drawable.icon_arrow
                    title = "Item3"
                    description = "3"
                    onClick { owner.showToast("click item3") }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private fun setting2(viewManager: ViewManager) = with(viewManager) {
        settings {
            backgroundColor = resources.getColor(android.R.color.white)
            settingGroup {
                title = "Group1"
                for (i in 1..3) {
                    settingItem(SettingItem.DescriptionType.TEXT) {
                        leftIconResId = resources.getIdentifier("icon$i", "drawable", context.packageName)
                        title = "Item$i"
                        description = "Description$i"
                        onClick { context.toast("click item$i") }
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private fun setting3(viewManager: ViewManager) = with(viewManager) {
        settings {
            backgroundColor = resources.getColor(android.R.color.white)

            settingGroup {
                title = "Group1"
                topSeperatorHeight = dip(10)

                for (i in 1..5) {
                    settingItem(descriptionType = SettingItem.DescriptionType.TEXT) {
                        if (i % 2 == 0) {
                            leftIconResId = resources.getIdentifier("icon$i", "drawable", context.packageName)
                            backgroundColor = resources.getColor(R.color.red_f5b7b1)
                            rightIconResId = R.drawable.icon_arrow
                        }
                        title = "Group1:Item$i"
                        description = "description$i"
                    }
                }
            }

            settingGroup {
                title = "Group2"
                topSeperatorHeight = dip(20)
                for (i in 1..5) {
                    settingItem {
                        if (i % 2 == 1) {
                            backgroundColor = resources.getColor(R.color.blue_b7d9f5)
                            rightIconResId = R.drawable.icon_arrow
                        }
                        title = "Group2:Item$i"
                        leftIconResId = resources.getIdentifier("icon$i", "drawable", context.packageName)
                    }
                }
            }

            settingGroup {
                title = "Group3"
                topSeperatorHeight = dip(50)
                for (i in 1..5) {
                    settingItem(descriptionType = SettingItem.DescriptionType.BADGE) {
                        if (i % 2 == 1) {
                            backgroundColor = resources.getColor(R.color.grey_777777)
                        }
                        title = "Group3:Item$i"
                        description = "$i"
                    }
                }
            }
        }
    }
}
