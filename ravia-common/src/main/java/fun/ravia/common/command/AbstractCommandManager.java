package fun.ravia.common.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import fun.ravia.common.CommonPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public abstract class AbstractCommandManager extends JavaPlugin {

    private final CommonPlugin commonPlugin;
    private final PaperCommandManager paperCommandManager;

    protected AbstractCommandManager(final CommonPlugin commonPlugin) {
        this.commonPlugin = commonPlugin;
        this.paperCommandManager = new PaperCommandManager(this);
    }

    private <T> void registerCommand(final BaseCommand baseCommand) {
        this.paperCommandManager.registerCommand(baseCommand);
    }

    public <T> void registerCommands(final BaseCommand... baseCommands) {
        Arrays.stream(baseCommands).forEachOrdered(this::registerCommand);
    }
}
