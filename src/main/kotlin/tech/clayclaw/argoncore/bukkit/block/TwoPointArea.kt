package tech.clayclaw.argoncore.bukkit.block

import dev.reactant.reactant.extensions.locationOf
import org.bukkit.Location

class TwoPointArea(
        val firstLocation : Location,
        val secondLocation : Location
) {

    /*
    fun copyTo(startLocation: Location) {
        StructureBlockApi.INSTANCE.structurePersistenceService.let { service ->
            service.createSaveConfiguration(
                    "clayclaw",
                    startLocation.world?.name?.toLowerCase(),
                    startLocation.world?.name
            ).let { config ->
                service.save(config, firstLocation, secondLocation)
                service.load(config, startLocation)
            }
        }
    }
     */

    fun findHighestPoint(bottom : Location): Location {
        val xLength = maxOf(firstLocation.blockX, secondLocation.blockX) - minOf(firstLocation.blockX, secondLocation.blockX)
        val yLength = maxOf(firstLocation.blockY, secondLocation.blockY) - minOf(firstLocation.blockY, secondLocation.blockY)
        val zLength = maxOf(firstLocation.blockZ, secondLocation.blockZ) - minOf(firstLocation.blockZ, secondLocation.blockZ)
        return locationOf(
                bottom.world,
                (bottom.blockX + xLength).toDouble(),
                (bottom.blockY + yLength).toDouble(),
                (bottom.blockZ + zLength).toDouble()
        )
    }

}
