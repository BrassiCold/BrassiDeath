package `fun`.brassicold.brassi.death.project

import `fun`.brassicold.brassi.death.project.internal.BrassiDeathLoader
import `fun`.brassicold.brassi.death.project.internal.core.module.VerifyModule
import org.yaml.snakeyaml.Yaml
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object BrassiDeath : Plugin() {

    @Config("setting.yml", migrate = true)
    lateinit var setting: ConfigFile

    val yaml = Yaml()

    override fun onLoad() {
        BrassiDeathLoader.load()
    }

    override fun onEnable() {
        BrassiDeathLoader.enable()
    }

    override fun onDisable() {
        BrassiDeathLoader.disable()
    }
}