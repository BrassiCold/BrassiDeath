package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath
import `fun`.brassicold.brassi.death.project.internal.core.manager.CentralManager
import `fun`.brassicold.brassi.death.project.internal.event.PluginImplEvent
import `fun`.brassicold.brassi.death.project.internal.event.PluginReloadEvent
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Cancellable
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.allWorlds
import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.module.lang.sendErrorMessage
import taboolib.module.lang.sendLang
import taboolib.platform.util.bukkitPlugin
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object EventModule {
    @SubscribeEvent
    fun pluginReload(event: PluginReloadEvent) {
        BrassiDeath.setting.reload()
        CentralManager.Conf_List = ConfModule.loadFiles()!!
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
                if (!(worldConf.exists() && worldConf.isFile)) {
                    worldConf.createNewFile()
                    worldConf.writeText(defContent)
                    console().sendLang("plugin-format", pluginId, "|-世界: $serverWorld 载人成功!")
                } else {
                    console().sendLang("plugin-format", pluginId, "|-世界: $serverWorld 已经载入过了!")
                }
            }
        } catch (e: FileNotFoundException) {
            console().sendErrorMessage("&4你这默认配置文件保熟吗？ -> def.yml")
            console().sendErrorMessage("&4${e.message}")
        } catch (e: IOException) {
            console().sendErrorMessage("&4你这世界文件夹保熟吗？ -> worlds/")
            console().sendErrorMessage("&4${e.message}")
        } catch (e: NullPointerException) {
            console().sendErrorMessage("&4你这默认配置文件保熟吗？ -> def.yml")
            console().sendErrorMessage("&4${e.message}")
        }

    }
    @SubscribeEvent(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun playerDeath(event: PlayerDeathEvent) {
        CentralManager.Conf_Event = event
        CentralManager.preProcessor()
    }
}