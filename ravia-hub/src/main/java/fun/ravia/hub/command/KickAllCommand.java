package fun.ravia.hub.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import fun.ravia.common.helper.message.MessageHelper;
import fun.ravia.hub.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class KickAllCommand extends BaseCommand {

    private final HubPlugin hubPlugin;

    public KickAllCommand(final HubPlugin hubPlugin) {
        this.hubPlugin = hubPlugin;
    }

    @CommandAlias("kickall|wyrzuc|kall")
    @CommandPermission("ravia.admin.hub.kickall")
    public void onKickAllCommand(final Player player, final String... args) {
        if (args.length == 0) {
            MessageHelper.createMessage(this.hubPlugin.getConfiguration().kickAllCommandUsageMessage)
                    .sendMessage(player);
            return;
        }

        final String reasonMessage = Arrays.stream(args)
                .map(arg -> arg + " ")
                .collect(Collectors.joining());
        Bukkit.getOnlinePlayers().forEach(players -> players.kickPlayer(MessageHelper.colored(reasonMessage)));
    }
}
