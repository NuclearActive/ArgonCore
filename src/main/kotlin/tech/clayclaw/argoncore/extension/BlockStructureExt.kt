package tech.clayclaw.argoncore.extension

import dev.reactant.reactant.utils.content.area.RectangularPrismArea
import dev.reactant.reactant.utils.content.area.WorldArea
import tech.clayclaw.argoncore.bukkit.block.TwoPointArea

fun TwoPointArea.asArea() : WorldArea<RectangularPrismArea> {
    return RectangularPrismArea(
            firstLocation.toVector(),
            secondLocation.toVector()
    ).toWorldArea(
            firstLocation.world ?: throw IllegalArgumentException("World cannot be null")
    )
}
