package fun.ravia.hub.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import fun.ravia.common.helper.message.MessageHelper;
import fun.ravia.hub.HubPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeCommand extends BaseCommand {

    private final HubPlugin hubPlugin;

    public GameModeCommand(final HubPlugin hubPlugin) {
        this.hubPlugin = hubPlugin;
    }

    @Default
    @CommandAlias("gamemode||gm|gmc")
    @CommandPermission("ravia.admin.hub.gamemode")
    public void onGameModeCommand(final Player player, final String... args) {
        if (args.length == 0) {
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().gameModeCommandUsageMessage)
                    .sendMessage(player);
            return;
        }

        final GameMode gameMode = GameMode.getByValue(Integer.parseInt(args[0]));
        if (gameMode == null) return;

        player.setGameMode(gameMode);
        MessageHelper.createMessage(this.hubPlugin.getConfiguration().gameModeCommandModeChangedMessage
                .replace("{GAMEMODE}", gameMode.name()))
                .sendMessage(player);
    }
}
