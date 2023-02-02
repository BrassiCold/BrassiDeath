package `fun`.brassicold.brassi.death.project.util

import `fun`.brassicold.brassi.death.project.internal.core.module.ConfModule
import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.module.lang.sendLang
import taboolib.platform.util.bukkitPlugin
import java.io.File

object ToolsUtil {
    fun tell(message: String = "message") {
        if (ConfModule.Setting_Debug)
            return console().sendLang("debug-format", pluginId, message)
    }
    fun worldNumber(): Int {
        val pluginFolder by lazy { bukkitPlugin.dataFolder }
        val worldsFolder by lazy { File(pluginFolder, "worlds") }
        val ymlFilesDef by lazy { worldsFolder.listFiles { file -> file.extension == "yml" } }
        val ymlFiles by lazy { ymlFilesDef.filter { file -> file.name != "def.yml" } }
        return ymlFiles.size
    }

    fun pluginMode(): String {
        if (ConfModule.Setting_Debug) {
            return "Debug"
        }
        return "Normal"
    }
}