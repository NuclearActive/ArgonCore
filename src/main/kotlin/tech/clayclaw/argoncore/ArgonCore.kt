package tech.clayclaw.argoncore

import dev.reactant.reactant.core.ReactantPlugin
import dev.reactant.reactant.extensions.translateChatColor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.bukkit.plugin.java.JavaPlugin

@ReactantPlugin([
    "tech.clayclaw.argoncore",
    "dev.reactant.modulardata"
])
class ArgonCore : JavaPlugin() {

    init {
        instance = this
    }

    override fun onEnable() {
        log.info("\"\\n\" +\n" +
                "                \"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\\n\" +\n" +
                "                \"─██████████████─████████████████───██████████████─██████████████─██████──────────██████─██████████████─██████████████─████████████████───██████████████─\\n\" +\n" +
                "                \"─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─\\n\" +\n" +
                "                \"─██░░██████░░██─██░░████████░░██───██░░██████████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─██░░██████░░██─██░░████████░░██───██░░██████████─\\n\" +\n" +
                "                \"─██░░██──██░░██─██░░██────██░░██───██░░██─────────██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────██░░██──██░░██─██░░██────██░░██───██░░██─────────\\n\" +\n" +
                "                \"─██░░██████░░██─██░░████████░░██───██░░██─────────██░░██──██░░██─██░░██──██░░██──██░░██─██░░██─────────██░░██──██░░██─██░░████████░░██───██░░██████████─\\n\" +\n" +
                "                \"─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░██──██████─██░░██──██░░██─██░░██──██░░██──██░░██─██░░██─────────██░░██──██░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─\\n\" +\n" +
                "                \"─██░░██████░░██─██░░██████░░████───██░░██──██░░██─██░░██──██░░██─██░░██──██░░██──██░░██─██░░██─────────██░░██──██░░██─██░░██████░░████───██░░██████████─\\n\" +\n" +
                "                \"─██░░██──██░░██─██░░██──██░░██─────██░░██──██░░██─██░░██──██░░██─██░░██──██░░██████░░██─██░░██─────────██░░██──██░░██─██░░██──██░░██─────██░░██─────────\\n\" +\n" +
                "                \"─██░░██──██░░██─██░░██──██░░██████─██░░██████░░██─██░░██████░░██─██░░██──██░░░░░░░░░░██─██░░██████████─██░░██████░░██─██░░██──██░░██████─██░░██████████─\\n\" +\n" +
                "                \"─██░░██──██░░██─██░░██──██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██████████░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░░░░░██─██░░░░░░░░░░██─\\n\" +\n" +
                "                \"─██████──██████─██████──██████████─██████████████─██████████████─██████──────────██████─██████████████─██████████████─██████──██████████─██████████████─\\n\" +\n" +
                "                \"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\"")
        log.info("&2Powered by &cClayClaw".translateChatColor())
    }

    companion object {
        @JvmStatic
        val log: Logger = LogManager.getLogger("ArgonCore")
        @JvmStatic
        lateinit var instance: ArgonCore
    }

}
