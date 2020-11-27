package tech.clayclaw.argoncore.bukkit.block

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.github.shynixn.structureblocklib.api.enumeration.StructureMirror
import com.github.shynixn.structureblocklib.api.enumeration.StructureRotation
import org.bukkit.Location
import tech.clayclaw.argoncore.ArgonCore
import java.io.File

class BlockStructureLoad(
        val at: Location
) {

    init {
        fileName("default.nbt")
    }

    var includeEntities: Boolean = false
    var seed: Long = 50L
    var integrity: Float = 0.2F
    var mirror: StructureMirror = StructureMirror.NONE
    var rotation: StructureRotation = StructureRotation.NONE

    private lateinit var file: File
    fun fileName(fileName: String) {
        val folder = File("plugins/ArgonCore/structure")
        if(!folder.exists()) folder.mkdirs()

        file = File("plugins/ArgonCore/structure/${fileName}")
    }

    private var onException: (Throwable) -> Unit = {}
    private var onProgress: (Double) -> Unit = {}
    private var onResult: (Void) -> Unit = {}

    internal fun start() {
        StructureBlockLibApi.INSTANCE
                .loadStructure(ArgonCore.instance)
                .at(at)
                .includeEntities(includeEntities)
                .seed(seed)
                .integrity(integrity)
                .mirror(mirror)
                .rotation(rotation)
                .loadFromFile(file)
                .onException(onException)
                .onProgress(onProgress)
                .onResult(onResult)
    }

}

fun Location.loadStructure(editing: BlockStructureLoad.() -> Unit)
        = BlockStructureLoad(this).apply(editing).start()
