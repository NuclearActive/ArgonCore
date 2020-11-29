package tech.clayclaw.argoncore.bukkit.chestui

import tech.clayclaw.argoncore.bukkit.chestui.component.Slot
import tech.clayclaw.argoncore.bukkit.chestui.component.SlotRange

fun slotOf(x: Int, y: Int) = Slot(x, y)

fun SlotRange.runRecursively(action: (Slot) -> Unit) {
    (cornerA.x..cornerB.x).forEach { x ->
        (cornerA.y..cornerB.y).forEach { y ->
            action(slotOf(x, y))
        }
    }
}
