## Overview
This is an example project to explain how to write a DSL with Kotlin. This DSL can create setting UI with a flexible way

## Example 
```
viewManager.settings {
    backgroundColor = resources.getColor(R.color.grey_777777)
    settingGroup {
        settingItem {
            leftIconResId = R.drawable.icon1
            rightIconResId = R.drawable.icon_arrow
            title = "Title1"
        }

        settingItem(descriptionType = SettingItem.DescriptionType.TEXT) {
            leftIconResId = R.drawable.icon2
            rightIconResId = R.drawable.icon_arrow
            title = "Title2"
            description = "Description2"
        }

        settingItem(descriptionType = SettingItem.DescriptionType.BADGE) {
            leftIconResId = R.drawable.icon3
            rightIconResId = R.drawable.icon_arrow
            title = "Title3"
            description = "3"
        }
    }
}
```
