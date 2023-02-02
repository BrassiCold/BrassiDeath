package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath

object ConfModule {
    val Setting_Debug: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.debug")
    val Setting_Async: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.concurrent-async")
    val Setting_KeepItem: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.keep-item")
    val Setting_AutoRegeneration: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.auto-regeneration")
}