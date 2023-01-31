package `fun`.brassicold.brassi.death.project.internal

import taboolib.common.platform.function.console
import taboolib.common.platform.function.pluginId
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.lang.sendLang

object BrassiDeathLoader {
    fun load() {
        console().sendLang("plugin-load", pluginId)
    }
    fun enable() {

        console().sendMessage("")
        console().sendMessage("§b▄▄▄▄· ▄▄▄   ▄▄▄· .▄▄ · .▄▄ · ▪  ·▄▄▄▄  ▄▄▄ . ▄▄▄· ▄▄▄▄▄ ▄ .▄")
        console().sendMessage("§b▐█ ▀█▪▀▄ █·▐█ ▀█ ▐█ ▀. ▐█ ▀. ██ ██▪ ██ ▀▄.▀·▐█ ▀█ •██  ██▪▐█")
        console().sendMessage("§b▐█▀▀█▄▐▀▀▄ ▄█▀▀█ ▄▀▀▀█▄▄▀▀▀█▄▐█·▐█· ▐█▌▐▀▀▪▄▄█▀▀█  ▐█.▪██▀▐█")
        console().sendMessage("§b██▄▪▐█▐█•█▌▐█ ▪▐▌▐█▄▪▐█▐█▄▪▐█▐█▌██. ██ ▐█▄▄▌▐█ ▪▐▌ ▐█▌·██▌▐▀")
        console().sendMessage("§b·▀▀▀▀ .▀  ▀ ▀  ▀  ▀▀▀▀  ▀▀▀▀ ▀▀▀▀▀▀▀▀•  ▀▀▀  ▀  ▀  ▀▀▀ ▀▀▀ ·")
        console().sendMessage("")


        console().sendLang("plugin-enable", pluginId)
    }
    fun disable() {
        console().sendLang("plugin-disable", pluginId)
    }
}