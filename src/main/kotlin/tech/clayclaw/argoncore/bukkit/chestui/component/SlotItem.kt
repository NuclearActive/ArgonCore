package tech.clayclaw.argoncore.bukkit.chestui.component

import dev.reactant.reactant.utils.content.item.itemStackOf
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import tech.clayclaw.argoncore.bukkit.chestui.slotOf

data class SlotItem(
        val slot: Slot,
        val itemStack: ItemStack
)

class SlotItemBuilder {
    var slot: Slot = slotOf(1,1)
    var itemStack: ItemStack = itemStackOf(Material.GRASS_BLOCK)

    internal fun toSlotItem() = SlotItem(slot, itemStack)
}
