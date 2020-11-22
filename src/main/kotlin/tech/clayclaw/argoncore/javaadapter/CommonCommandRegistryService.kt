package tech.clayclaw.argoncore.javaadapter

import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleInspector
import dev.reactant.reactant.core.dependency.injection.producer.ComponentProvider
import dev.reactant.reactant.extra.command.PicocliCommandService
import dev.reactant.reactant.extra.command.ReactantCommand

@Component
class CommonCommandRegistryService(
        private val picocliCommandService: PicocliCommandService
): LifeCycleInspector {

    private val builderList: HashSet<CommandBuilder> = hashSetOf()

    fun newBuilder(coreCommand: ReactantCommand) = CommandBuilder(coreCommand)
    fun register(builder: CommandBuilder) { builderList.add(builder) }

    override fun afterEnable(componentProvider: ComponentProvider<Any>) {
        registerAll()
    }

    private fun registerAll() {
        picocliCommandService {
            builderList.forEach { builder ->
                command({ builder.coreCommand }) {
                    builder.subCommands.forEach { subCommand -> command({ subCommand }) }
                }
            }
        }

        builderList.clear()
    }

}
