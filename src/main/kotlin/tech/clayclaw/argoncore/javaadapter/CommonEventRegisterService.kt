package tech.clayclaw.argoncore.javaadapter

import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleHook
import dev.reactant.reactant.core.dependency.injection.components.Components
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import tech.clayclaw.argoncore.ArgonCore

@Component
class CommonEventRegisterService(
        private val components: Components<Listener>
): LifeCycleHook {

    override fun onEnable() {
        components.forEach {
            Bukkit.getPluginManager().registerEvents(it, ArgonCore.instance)
        }
    }

}
