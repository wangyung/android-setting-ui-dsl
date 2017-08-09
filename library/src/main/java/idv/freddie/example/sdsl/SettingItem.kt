/*
 * Copyright (c) 2017 LINE Corporation. All rights Reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package idv.freddie.example.sdsl

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import idv.freddie.example.R
import idv.freddie.example.useRippleEffect
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._FrameLayout
import org.jetbrains.anko.alignParentRight
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.centerVertically
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dimen
import org.jetbrains.anko.dip
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageView
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.leftOf
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.rightOf
import org.jetbrains.anko.rightPadding
import org.jetbrains.anko.sp
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

class SettingItem   (ctx: Context) : _FrameLayout(ctx) {
    internal lateinit var leftIconView: ImageView
    internal var rightIconView: ImageView? = null
    internal var descriptionLabel: TextView? = null
    internal var descriptionContainer: ViewGroup? = null

    internal lateinit var titleView: TextView

    var rightIconResId: Int?
        set(value) {
            value?.let {
                rightIconView?.imageResource = it
                rightIconView?.visibility = View.VISIBLE
            }
        }
        get() = AnkoInternals.noGetter()

    var leftIconResId: Int?
        set(value) { value?.let { leftIconView.imageResource = it} }
        get() = AnkoInternals.noGetter()

    var title: String
        set(value) { titleView.text = value }
        get() = titleView.text.toString()

    var description: String
        set(value) {
            if (value.isEmpty()) {
                descriptionLabel?.visibility = View.INVISIBLE
                descriptionContainer?.visibility = View.INVISIBLE
            } else {
                descriptionContainer?.visibility = View.VISIBLE
                descriptionLabel?.visibility = View.VISIBLE
            }
            descriptionLabel?.text = value
        }
        get() = if (descriptionLabel?.text == null) "" else descriptionLabel?.text.toString()

    /**
     * Description type
     */
    enum class DescriptionType {
        /**
         * Text style
         */
        TEXT,
        /**
         * Badge style
         */
        BADGE,
        /**
         * No description UI
         */
        NONE
    }

    init {
        isClickable = true
    }
}

internal fun SettingGroup.settingItemLayout(context: Context,
                                            descriptionType: SettingItem.DescriptionType = SettingItem.DescriptionType.NONE,
                                            showRightIcon: Boolean = true): SettingItem {
    return ankoView(::SettingItem, 0) {
        backgroundResource = R.drawable.gray_e9e9e9_top_bottom_stroke_white_bg
        frameLayout {
            //support ripple effect to look better
            useRippleEffect = true
            relativeLayout {
                //internal divider
                setBackgroundResource(R.drawable.gray_e9e9e9_top_bottom_stroke_transparent_bg)
                minimumHeight = dip(50)
                rightPadding = dimen(R.dimen.default_padding)
                leftIconView = imageView {
                    id = R.id.setting_item_left_icon
                }.lparams {
                    centerVertically()
                    margin = dip(8)
                }
                //FIXME: cannot find R.style.SettingItemText here, don't know why....
                titleView = textView {
                    id = R.id.setting_item_title
                    textSize = sp(5).toFloat()
                    textColor = resources.getColor(R.color.dark_grey_1d1d1d)
                }.lparams {
                    centerVertically()
                    rightOf(R.id.setting_item_left_icon)
                }
                rightIconView = imageView {
                    id = R.id.setting_item_right_icon
                    maxWidth = dip(20)
                    maxHeight = dip(20)
                    if (!showRightIcon) {
                        visibility = View.GONE
                    }
                }.lparams {
                    centerVertically()
                    alignParentRight()
                }

                when (descriptionType) {
                    SettingItem.DescriptionType.TEXT -> {
                        descriptionLabel = textView {
                            textSize = sp(5).toFloat()
                            maxLines = 1
                            ellipsize = TextUtils.TruncateAt.END
                            textColor = resources.getColor(R.color.dark_grey_1d1d1d_opacity_60)
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent) {
                            centerVertically()
                            if (showRightIcon) {
                                rightMargin = dip(20)
                                leftMargin = dip(8)
                                rightOf(R.id.setting_item_title)
                            } else {
                                alignParentRight()
                            }
                        }
                    }
                    SettingItem.DescriptionType.BADGE -> {
                        descriptionContainer = frameLayout {
                            visibility = View.INVISIBLE
                            background = ContextCompat.getDrawable(context, R.drawable.selection_badge_red_bg)
                            descriptionLabel = textView {
                                textSize = sp(3.5f).toFloat()
                                textColor = Color.WHITE
                                includeFontPadding = false
                            }.lparams(width = wrapContent, height = dimen(R.dimen.setting_badge_height))
                        }.lparams {
                            centerVertically()
                            if (showRightIcon) {
                                rightMargin = dip(11)
                                leftOf(R.id.setting_item_right_icon)
                            } else {
                                alignParentRight()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }.lparams(width = matchParent, height = matchParent) {
                leftMargin = dimen(R.dimen.default_padding)
            }
        }.lparams(width = matchParent, height = matchParent)
    }
}

fun SettingGroup.settingItem(descriptionType: SettingItem.DescriptionType = SettingItem.DescriptionType.NONE,
                             init: (@AnkoViewDslMarker SettingItem).() -> Unit): SettingItem {
    val settingItem = settingItemLayout(context = this.context, descriptionType = descriptionType)
    settingItem.init()
    return settingItem
}
