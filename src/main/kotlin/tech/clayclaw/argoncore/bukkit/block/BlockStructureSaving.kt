package tech.clayclaw.argoncore.bukkit.block

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.github.shynixn.structureblocklib.api.enumeration.StructureRestriction
import org.bukkit.Location
import org.bukkit.util.Vector
import tech.clayclaw.argoncore.ArgonCore
import java.io.File
import java.nio.file.Path

class BlockStructureSaving(
        val at: Location
) {

    init {
        fileName("default.nbt")
    }

    var author = "clayclaw"

    var sizeX: Int = 0
    var sizeY: Int = 0
    var sizeZ: Int = 0
    var offset: Vector? = null

    var includeEntities: Boolean = false
    var restriction: StructureRestriction = StructureRestriction.UNLIMITED

    private lateinit var path: Path
    fun fileName(fileName: String) {
        val folder = File("plugins/ArgonCore/structure")
        if(!folder.exists()) folder.mkdirs()

        path = File("plugins/ArgonCore/structure/${fileName}").toPath()
    }

    private var onException: (Throwable) -> Unit = {}
    private var onProgress: (Double) -> Unit = {}
    private var onResult: (Void) -> Unit = {}

    internal fun start() {
        StructureBlockLibApi.INSTANCE
                .saveStructure(ArgonCore.instance)
                .at(at)
                .author(author)
                .includeEntities(includeEntities)
                .sizeX(sizeX)
                .sizeY(sizeY)
                .sizeZ(sizeZ)
                .offSet(offset)
                .restriction(restriction)
                .saveToPath(path)
                .onException(onException)
                .onProgress(onProgress)
                .onResult(onResult)
    }

}

fun Location.saveStructure(editing: BlockStructureSaving.() -> Unit)
        = BlockStructureSaving(this).apply(editing).start()
