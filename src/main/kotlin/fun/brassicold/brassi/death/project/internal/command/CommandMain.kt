package `fun`.brassicold.brassi.death.project.internal.command

import `fun`.brassicold.brassi.death.project.internal.event.PluginImplEvent
import `fun`.brassicold.brassi.death.project.internal.event.PluginReloadEvent
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
}