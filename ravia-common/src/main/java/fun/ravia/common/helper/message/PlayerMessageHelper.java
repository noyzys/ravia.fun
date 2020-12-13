package fun.ravia.common.helper.message;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class PlayerMessageHelper {

    private final String contentMessage;

    @Contract(pure = true)
    public PlayerMessageHelper(final String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public void sendMessage(@NotNull final CommandSender commandSender) {
        commandSender.sendMessage(this.contentMessage);
    }

    public void sendMessage(@NotNull final Player player) {
        player.sendMessage(this.contentMessage);
    }

    public void messageStyle(@NotNull final Player player, final MessageStyle messageStyle) {
        if (this.contentMessage.contains(MessageStyle.ACTIONBAR + "")) MessageHelper.createActionbarPacketMessage(player, MessageHelper.colored(this.contentMessage));
    }
}
