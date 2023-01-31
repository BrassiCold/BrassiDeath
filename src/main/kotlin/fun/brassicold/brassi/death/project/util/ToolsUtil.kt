package `fun`.brassicold.brassi.death.project.util

import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.module.lang.sendLang

object ToolsUtil {
    fun tell(message: String = "message") {
        if (SettingManager.Setting_Debug)
            return console().sendLang("debug-format", pluginId, message)
    }
}