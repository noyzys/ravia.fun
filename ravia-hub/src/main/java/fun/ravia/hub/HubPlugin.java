package fun.ravia.hub;

import co.aikar.commands.PaperCommandManager;
import fun.ravia.common.configuration.ConfigurationLoader;
import fun.ravia.hub.command.GameModeCommand;
import fun.ravia.hub.command.KickAllCommand;
import fun.ravia.hub.command.WhiteListCommand;
import fun.ravia.hub.listener.HubListener;
import io.vavr.collection.List;
import org.bukkit.plugin.java.JavaPlugin;

public final class HubPlugin extends JavaPlugin {

    private HubConfiguration configuration;

    @Override
    public void onEnable() {
        this.configuration = ConfigurationLoader.loadConfiguration(this.getDataFolder(), HubConfiguration.class, "config");

        final PaperCommandManager commandManager = new PaperCommandManager(this);
        this.registerCommandsHub(commandManager);
        new HubListener(this);
        saveDefaultConfig();
    }

    private void registerCommandsHub(final PaperCommandManager commandManager) {
        List.of(new GameModeCommand(this),
                new KickAllCommand(this),
                new WhiteListCommand(this))
                .forEach(commandManager::registerCommand);
    }

    public HubConfiguration getConfiguration() {
        return configuration;
    }
}
