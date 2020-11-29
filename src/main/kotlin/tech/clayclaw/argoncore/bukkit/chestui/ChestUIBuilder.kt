package tech.clayclaw.argoncore.bukkit.chestui

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import tech.clayclaw.argoncore.bukkit.chestui.component.SlotItem
import tech.clayclaw.argoncore.bukkit.chestui.component.SlotItemBuilder

class ChestUIBuilder {

    private val itemPattern: HashSet<SlotItem> = hashSetOf()

    var row = 6
    var title: String = "Unknown"

    internal fun assembleInventory(): Inventory {
        val inv: Inventory = Bukkit.createInventory(null, row * 9 - 1, title)

        return inv
    }

    fun item(item: SlotItemBuilder.() -> Unit) {
        itemPattern.add(SlotItemBuilder().apply(item).toSlotItem())
    }

    fun fill(item: SlotItemBuilder.() -> Unit) {
        (1..6).forEach { x ->
            (1..9).forEach { y ->
                itemPattern.add(SlotItemBuilder().apply(item).apply {
                    slot = slotOf(x, y)
                }.toSlotItem())
            }
        }
    }

}
