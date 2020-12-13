package fun.ravia.common.helper.message;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class MessageHelper {

    @Contract(pure = true)
    private MessageHelper() {
    }

    @NotNull
    public static String colored(@NotNull final String contentMessage) {
        return ChatColor.translateAlternateColorCodes('&', contentMessage
                .replace(">>", "\u00BB")
                .replace("<<", "\u00AB"));
    }

    @NotNull
    public static List<String> colored(@NotNull final List<String> contentMessages) {
        return contentMessages
                .stream()
                .map(MessageHelper::colored)
                .collect(Collectors.toList());
    }

    @NotNull
    public static String toString(@NotNull Collection<String> collectionContentMessages) {
        return colored(collectionContentMessages
                .toString()
                .replace(", ", "\n")
                .replace("[", "")
                .replace("]", ""));
    }

    @NotNull
    public static PlayerMessageHelper createMessage(@NotNull final String contentMessage) {
        return new PlayerMessageHelper(colored(contentMessage));
    }

    public static void createActionbarPacketMessage(@NotNull final Player player, @NotNull final String contentMessage) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colored(contentMessage) + "\"}"), (byte) 2));
    }
}
