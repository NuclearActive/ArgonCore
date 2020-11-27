package tech.clayclaw.argoncore.extension

import org.bukkit.Location
import tech.clayclaw.argoncore.bukkit.block.TwoPointArea

fun Location.formTwoPointArea(another: Location) = TwoPointArea(this, another)
