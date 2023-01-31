package `fun`.brassicold.brassi.death.project.util

import `fun`.brassicold.brassi.death.project.BrassiDeath

object SettingManager {
    val Setting_Debug: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.debug")
    val Setting_Async: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.concurrent-async")
    val Setting_KeepItem: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.keep-item")
}