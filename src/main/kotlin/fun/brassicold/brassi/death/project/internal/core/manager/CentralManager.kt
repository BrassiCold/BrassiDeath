package `fun`.brassicold.brassi.death.project.internal.core.manager

import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.platform.util.bukkitPlugin
import java.io.File

object CentralManager {

    fun preProcessor(event: PlayerDeathEvent): String {
        val startTime = System.currentTimeMillis()
        ToolsUtil.tell("---[BrassiDeath 正在进行预处理中...]---")

        val deathEntity by lazy { event.entity }
        val deathWorld by lazy { deathEntity.world.name }
        ToolsUtil.tell("获取玩家死亡世界: $deathWorld | Type: ${deathWorld::class.simpleName}")
        val worldFiles by lazy { File(bukkitPlugin.dataFolder, "worlds") }
        val worldList: ArrayList<String> by lazy { worldFiles.listFiles()?.toString() as ArrayList<String> }
        ToolsUtil.tell("获取世界配置列表: $worldFiles | Type: ${worldFiles::class.simpleName}")
        if (deathWorld !in worldList) {
            ToolsUtil.tell("当前世界 -> [${deathWorld}] | 暂未被BrassiDeath接管!")
            return "不在接管世界中"
        }
        ToolsUtil.tell("当前玩家死亡世界处于接管世界中...")

        //死亡不掉落物品
        event.keepInventory = true
        //死亡不掉落经验
        event.keepLevel = true

        val endTime = System.currentTimeMillis()
        val costTime = endTime - startTime
        ToolsUtil.tell("---[BrassiDeath 预处理完毕! 耗时 $costTime ms]---")
        coreProcessor()
        return "处理完毕"
    }

    private fun coreProcessor() {
        postProcessor()
    }

    private fun postProcessor() {

    }
}