package tech.clayclaw.argoncore.extension

import org.bukkit.Bukkit
import org.bukkit.event.Event

fun <T: Event> callEvent(event: T) = event.also { Bukkit.getServer().pluginManager.callEvent(it) }
