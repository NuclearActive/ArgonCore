package tech.clayclaw.argoncore.bukkit.chestui

import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleHook
import dev.reactant.reactant.service.spec.server.EventService
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

@Component
class ChestUIService(
        private val eventService: EventService
): LifeCycleHook {

    private val viewing: HashSet<Player> = hashSetOf()

    override fun onEnable() {
        eventService {

            InventoryCloseEvent::class.observable()

            InventoryDragEvent::class.observable()

        }
    }

    fun openUIView(viewer: Player, editing: ChestUIBuilder.() -> Unit) {

        viewing.add(viewer)
    }

}
