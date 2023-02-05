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
    fun worldCheck(type: String): Any? {
        val pluginFolder by lazy { bukkitPlugin.dataFolder }
        val worldsFolder by lazy { File(pluginFolder, "worlds") }
        val ymlFilesDef by lazy { worldsFolder.listFiles { file -> file.extension == "yml" } }
        val ymlFiles by lazy { ymlFilesDef.filter { file -> file.name != "def.yml" } }
        when (type) {
            "size" -> { return ymlFiles.size }
            "list" -> {return ymlFiles}
        }
        return null
    }

    fun pluginMode(): String {
        if (ConfModule.Setting_Debug) {
            return "Debug"
        }
        return "Normal"
    }

    fun checkFileName(list: ArrayList<*>): ArrayList<String> {
        val fileNameList by lazy { ArrayList<String>() }
        for (file in list) {
            val fileString = file.toString().split("/").toMutableList()
            val fileName = fileString[fileString.lastIndex].split(".").toMutableList()[0]
            fileNameList.add(fileName)
        }
        return fileNameList
    }
}