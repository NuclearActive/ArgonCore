package tech.clayclaw.argoncore.javaadapter;

import dev.reactant.reactant.extra.command.ReactantCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {

    private ReactantCommand coreCommand;
    private ArrayList<ReactantCommand> subCommands = new ArrayList<ReactantCommand>();

    public CommandBuilder(ReactantCommand coreCommand) {
        this.coreCommand = coreCommand;
    }

    protected ReactantCommand getCoreCommand() {
        return this.coreCommand;
    }

    protected List<ReactantCommand> getSubCommands() {
        return this.subCommands;
    }

    public CommandBuilder subCommand(ReactantCommand subCommand) {
        subCommands.add(subCommand);
        return this;
    }

    public void register(CommonCommandRegistryService registryService) {
        registryService.register(this);
    }

}
