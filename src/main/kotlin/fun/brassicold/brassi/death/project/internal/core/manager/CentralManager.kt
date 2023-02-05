package `fun`.brassicold.brassi.death.project.internal.core.manager

import `fun`.brassicold.brassi.death.project.BrassiDeath.yaml
import `fun`.brassicold.brassi.death.project.internal.core.module.VerifyModule
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.platform.util.bukkitPlugin
import java.io.File

object CentralManager {
    lateinit var Conf_List: MutableList<Map<String, Any>>
    lateinit var Conf_Event: PlayerDeathEvent
    private lateinit var deathWorld: String
    //获取相关参数
    fun preProcessor(): String {
        val startTime = System.currentTimeMillis()

        ToolsUtil.tell("---[BrassiDeath 正在进行预处理中...]---")
        val event by lazy { Conf_Event }
        val deathEntity by lazy { event.entity }
        deathWorld = deathEntity.world.name
        ToolsUtil.tell("获取玩家死亡世界: $deathWorld | Type: ${deathWorld::class.simpleName}")
        val worldFiles by lazy { File(bukkitPlugin.dataFolder, "worlds") }
        val worldList by lazy { worldFiles.listFiles()?.toMutableList() as ArrayList<*>  }
        ToolsUtil.tell("获取世界配置列表: $worldFiles | Type: ${worldFiles::class.simpleName}")

        if (deathWorld !in ToolsUtil.checkFileName(worldList)) {
            ToolsUtil.tell("当前世界 -> [${deathWorld}] | 暂未被BrassiDeath接管!")
            return "不在接管世界中"
        }
        val enable by lazy { VerifyModule.checkWorld(deathWorld) }
        if (!enable!!) { return "不在接管世界中" }
        ToolsUtil.tell("当前玩家死亡世界处于接管世界中...")
        val endTime = System.currentTimeMillis()
        val costTime = endTime - startTime
        ToolsUtil.tell("---[BrassiDeath 预处理完毕! 耗时 $costTime ms]---")
        coreProcessor()
        return "处理完毕"
    }


    //处理相关参数
    private fun coreProcessor() {
        ToolsUtil.tell("---[BrassiDeath 正在进行中处理中...]---")
        val startTime = System.currentTimeMillis()

        val worldName by lazy { deathWorld }
        val worldFileName by lazy { "$worldName.yml" }
        val worldConf by lazy { File(File(bukkitPlugin.dataFolder, "worlds"), worldFileName) }
        val dataLimit = yaml.load<Map<String, Any>>(worldConf.inputStream())
        val limitGroups = dataLimit["LimitGroups"] as List<Map<String, Any>>
        var order: Int = -1

        fun checkGroup(): Int? {
            for (group in limitGroups) {
                order += 1
                val hasPermission = group["permission"].toString()
                if (VerifyModule.checkPlayerPermission(Conf_Event.entity.player!!, hasPermission)) {
                    return order
                }
            }
            return null
        }
        fun defGroup() {

        }
        fun limitGroup(ord: Int) {

        }
        when (checkGroup()) {
            null -> {
                ToolsUtil.tell("|-当前死亡玩家没有所需权限o")
                defGroup()
            }
            else -> {
                limitGroup(order)
            }
        }

        val endTime = System.currentTimeMillis()
        val costTime = endTime - startTime
        ToolsUtil.tell("---[BrassiDeath 中处理完毕! 耗时 $costTime ms]---")
        postProcessor()
    }


    //执行死亡系统
    private fun postProcessor() {
        ToolsUtil.tell("---[BrassiDeath 正在进行尾处理中...]---")
        val startTime = System.currentTimeMillis()


        val endTime = System.currentTimeMillis()
        val costTime = endTime - startTime
        ToolsUtil.tell("---[BrassiDeath 尾处理完毕! 耗时 $costTime ms]---")
    }
}