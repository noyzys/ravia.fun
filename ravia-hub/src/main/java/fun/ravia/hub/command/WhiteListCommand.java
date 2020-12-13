package fun.ravia.hub.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import fun.ravia.common.configuration.ConfigurationLoader;
import fun.ravia.common.helper.message.MessageHelper;
import fun.ravia.hub.HubConfiguration;
import fun.ravia.hub.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WhiteListCommand extends BaseCommand {

    private final HubPlugin hubPlugin;

    public WhiteListCommand(final HubPlugin hubPlugin) {
        this.hubPlugin = hubPlugin;
    }

    @CommandAlias("whitelist|wh")
    @CommandPermission("ravia.admin.hub.whitelist")
    public void onWhiteListCommand(final Player player, final String... args) {
        if (args.length == 0) {
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandUsageMessage).sendMessage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 1) {
                MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandUsageMessage).sendMessage(player);
                return;
            }

            if (this.hubPlugin.getConfiguration().whiteListByPassList.contains(args[1])) {
                MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandPlayerIsAddedToWhiteListMessage)
                        .sendMessage(player);
                return;
            }

            this.hubPlugin.getConfiguration().whiteListByPassList.add(args[1]);
            ConfigurationLoader.loadConfiguration(this.hubPlugin.getDataFolder(), HubConfiguration.class, "config");
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandPlayerAddedToWhiteListMessage
                    .replace("{PLAYER}", args[1]))
                    .sendMessage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 1) {
                MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandUsageMessage).sendMessage(player);
                return;
            }

            if (!this.hubPlugin.getConfiguration().whiteListByPassList.contains(args[1])) {
                MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandPlayerIsRemovedWithWhiteListMessage).sendMessage(player);
                return;
            }

            this.hubPlugin.getConfiguration().whiteListByPassList.remove(args[1]);
            ConfigurationLoader.loadConfiguration(this.hubPlugin.getDataFolder(), HubConfiguration.class, "config");
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandPlayerRemovedWithWhiteListMessage
                    .replace("{PLAYER}", args[1]))
                    .sendMessage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("status")) {
            this.hubPlugin.getConfiguration().setWhiteListStatus(!this.hubPlugin.getConfiguration().isWhiteListStatus());
            Bukkit.setWhitelist(!this.hubPlugin.getConfiguration().isWhiteListStatus());
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandWhiteListIsEnabledDisabledMessage
                    + ((this.hubPlugin.getConfiguration().isWhiteListStatus()) ? "wlaczona" : "wylaczona"))
                    .sendMessage(player);
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandWhiteListSetStatusMessage
                    + ((this.hubPlugin.getConfiguration().isWhiteListStatus()) ? "wlaczona" : "wylaczona"))
                    .sendMessage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("list")) {
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandPersonsOnWhiteListMessage
                    .replace("{LIST}", String.join("", this.hubPlugin.getConfiguration().whiteListByPassList + ".") + ""))
                    .sendMessage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            this.hubPlugin.getConfiguration().whiteListByPassList.clear();
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().whiteListCommandCleanedListsWithWhiteListMessage).sendMessage(player);
        }
    }
}

