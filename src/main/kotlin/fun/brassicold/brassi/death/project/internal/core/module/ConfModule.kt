package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath
import `fun`.brassicold.brassi.death.project.BrassiDeath.yaml
import `fun`.brassicold.brassi.death.project.internal.core.manager.CentralManager
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.platform.util.bukkitPlugin
import java.io.File

object ConfModule {
    val Setting_Debug: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.debug")
    val Setting_Async: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.concurrent-async")
    val Setting_KeepItem: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.keep-item")
    val Setting_AutoRegeneration: Boolean
        get() = BrassiDeath.setting.getBoolean("Options.auto-regeneration")
    @Awake(LifeCycle.ACTIVE)
    fun loadFiles(): MutableList<Map<String, Any>>? {
        val listFiles = ToolsUtil.worldCheck("list") as ArrayList<File>
        if (listFiles.isEmpty()) {
            return null
        }
        val confList = mutableListOf<Map<String, Any>>()
        for (file in listFiles) {
            val conf = file.inputStream().use { yaml.load<Map<String, Any>>(it) }
            confList.add(conf)
            ToolsUtil.tell("|-成功将 ${file.name} 加载入内存中...")
        }
        ToolsUtil.tell("世界配置文件载入完毕!")
        CentralManager.Conf_List = confList
        return confList
    }
}