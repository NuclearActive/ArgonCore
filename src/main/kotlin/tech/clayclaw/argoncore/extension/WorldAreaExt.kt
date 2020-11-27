package tech.clayclaw.argoncore.extension

import dev.reactant.reactant.utils.content.area.RectangularPrismArea
import dev.reactant.reactant.utils.content.area.WorldArea
import tech.clayclaw.argoncore.bukkit.block.TwoPointArea

fun WorldArea<RectangularPrismArea>.asBlockStructure() : TwoPointArea {
    return TwoPointArea(
            area.minCorner.toLocation(world),
            area.maxCorner.toLocation(world)
    )
}
