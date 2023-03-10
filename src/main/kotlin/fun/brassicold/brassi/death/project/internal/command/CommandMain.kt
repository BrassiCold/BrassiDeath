package `fun`.brassicold.brassi.death.project.internal.command

import `fun`.brassicold.brassi.death.project.internal.core.module.VerifyModule
import `fun`.brassicold.brassi.death.project.internal.event.PluginImplEvent
import `fun`.brassicold.brassi.death.project.internal.event.PluginReloadEvent
import `fun`.brassicold.brassi.death.project.util.ToolsUtil
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.*
import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.expansion.createHelper
import taboolib.module.lang.sendLang


@CommandHeader(
    name = "brassideath",
    aliases = ["bd", "death"],
    permission = "brassideath.command",
    permissionDefault = PermissionDefault.OP,
    description = "BrassiDeath Main Command"
)

object CommandMain {
    @CommandBody(permission = "brassideath.command.help", permissionDefault = PermissionDefault.OP)
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "brassideath.command.reload", permissionDefault = PermissionDefault.OP)
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            PluginReloadEvent.call()
            sender.sendLang("command-reload", pluginId)
        }
    }

    @CommandBody(permission = "brassideath.command.impl", permissionDefault = PermissionDefault.OP)
    val impl = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            sender.sendLang("plugin-format", pluginId, "正在载入世界数据中...")
            PluginImplEvent.call()
            sender.sendLang("plugin-format", pluginId, "世界配置载入成功!")
        }
    }

    @CommandBody(permission = "brassideath.command.check", permissionDefault = PermissionDefault.OP)
    val check = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            VerifyModule.verifyWorldsFile()
            sender.sendLang("plugin-format", pluginId, "插件状态检测完毕!")
        }
    }

    @CommandBody(permission = "brassideath.command.info", permissionDefault = PermissionDefault.OP)
    val info = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            sender.sendMessage("§8------§7[§bBrassiDeath§7]§8------")
            sender.sendMessage("§8|- §e插件接管世界§7[§b${ToolsUtil.worldCheck("size")}§7]")
            sender.sendMessage("§8|- §e插件运行模式§7[§b${ToolsUtil.pluginMode()}§7]")
            sender.sendMessage("§8------§7[§bBrassiDeath§7]§8------")
        }
    }
}