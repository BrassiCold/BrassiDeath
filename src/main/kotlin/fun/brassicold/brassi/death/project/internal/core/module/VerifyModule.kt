package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath.yaml
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.lang.sendErrorMessage
import taboolib.module.lang.sendLang
import taboolib.platform.util.bukkitPlugin
import java.io.File
import java.io.FileInputStream

object VerifyModule {

    /**
    *  验证worlds目录下文件
     */
    @Awake(LifeCycle.ACTIVE)
    fun verifyWorldsFile(): Unit? {
        console().sendLang("plugin-format", pluginId, "正在执行世界文件检测...")
        ToolsUtil.tell("正在执行VerifyModule.verifyWorldFile中...")
        releaseResourceFile("worlds/def.yml", replace = true)
        ToolsUtil.tell("|-已成功释放资源文件...")
        val pluginFolder by lazy { bukkitPlugin.dataFolder }
        ToolsUtil.tell("|-获取dataFolder: $pluginFolder | Type: ${pluginFolder::class.simpleName}")
        val worldsFolder by lazy { File(pluginFolder, "worlds") }
        ToolsUtil.tell("|-获取worldsFile: $worldsFolder| Type: ${worldsFolder::class.simpleName}")
        val ymlFilesDef by lazy { worldsFolder.listFiles { file -> file.extension == "yml" } }
        ToolsUtil.tell("|-获取ymlFilesDef: $ymlFilesDef | Type: ${ymlFilesDef::class.simpleName}")
        val ymlFiles by lazy { ymlFilesDef.filter { file -> file.name != "def.yml" } }
        ToolsUtil.tell("|-已成功剔除默认def.yml文件...")
        ToolsUtil.tell("|-获取ymlFiles: $ymlFiles | Type: ${ymlFiles::class.simpleName}")
        ToolsUtil.tell("|-判断是否存在世界配置文件中...")
        if (ymlFiles.isEmpty()) {
            console().sendLang("plugin-format", pluginId, "不存在世界配置, 执行完毕！")
            console().sendLang("plugin-format", pluginId, "请使用/death impl 导入世界配置哦～")
            return null
        }
        ToolsUtil.tell("|-worlds目录下存在世界配置文件...")
        for (ymlFile in ymlFiles) {
            ToolsUtil.tell("|-正在检测世界配置文件: ${ymlFile.name} | Type: ${ymlFile::class.simpleName}")
            try {
                val inputStream by lazy { FileInputStream(ymlFile) }
                val fileData by lazy { yaml.load<Map<String, Any>>(inputStream) }
                val worldConf by lazy { fileData["WorldConf"] as Map<*, *> }
                val enable by lazy { worldConf["enable"] as Boolean }
                console().sendLang("verify-world-check", pluginId, ymlFile.name, enable )
            } catch (e: NullPointerException) {
                console().sendErrorMessage("&4你这世界配置文件保熟吗？ -> ${ymlFile.name}")
                console().sendErrorMessage("&4${e.message}")
            }
        }
        console().sendLang("plugin-format", pluginId, "世界配置文件检测完毕!")
        return null
    }
}