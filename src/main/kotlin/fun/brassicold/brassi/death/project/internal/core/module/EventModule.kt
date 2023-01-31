package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath
import `fun`.brassicold.brassi.death.project.internal.event.PluginImplEvent
import `fun`.brassicold.brassi.death.project.internal.event.PluginReloadEvent
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.allWorlds
import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.module.lang.sendErrorMessage
import taboolib.module.lang.sendLang
import taboolib.platform.util.bukkitPlugin
import java.io.File
import java.io.FileNotFoundException

object EventModule {
    @SubscribeEvent
    fun pluginReload(event: PluginReloadEvent) {
        BrassiDeath.setting.reload()
    }
    @SubscribeEvent
    fun pluginImpl(event: PluginImplEvent) {
        try {
            val serverWorlds by lazy { allWorlds() }
            ToolsUtil.tell("|-获取serverWorlds: $serverWorlds | Type: ${serverWorlds::class.simpleName}")
            val pluginFolder by lazy { bukkitPlugin.dataFolder }
            val worldsFolder by lazy { File(pluginFolder, "worlds/") }
            val defConf by lazy { File(worldsFolder, "def.yml") }
            val defContent by lazy { defConf.readText() }
            for (serverWorld in serverWorlds) {
                val worldConf by lazy { File(worldsFolder, "${serverWorld}.yml") }
                worldConf.createNewFile()
                worldConf.writeText(defContent)
                console().sendLang("plugin-format", pluginId, "|-世界: $serverWorld 载人成功!")
            }
        } catch (e: FileNotFoundException) {
            console().sendErrorMessage("&4你这默认配置文件保熟吗？ -> def.yml")
            console().sendErrorMessage("&4${e.message}")
        }

    }
}