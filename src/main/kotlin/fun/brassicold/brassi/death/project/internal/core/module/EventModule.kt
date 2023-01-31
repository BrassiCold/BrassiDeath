package `fun`.brassicold.brassi.death.project.internal.core.module

import `fun`.brassicold.brassi.death.project.BrassiDeath
import `fun`.brassicold.brassi.death.project.internal.event.PluginReloadEvent
import taboolib.common.platform.event.SubscribeEvent

object EventModule {
    @SubscribeEvent
    fun pluginReload(event: PluginReloadEvent) {
        BrassiDeath.setting.reload()
    }
}