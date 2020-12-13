package fun.ravia.hub.listener;

import fun.ravia.common.helper.message.MessageHelper;
import fun.ravia.hub.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class HubListener implements Listener {

    private final HubPlugin hubPlugin;

    public HubListener(final HubPlugin hubPlugin) {
        this.hubPlugin = hubPlugin;
        this.hubPlugin.getServer().getPluginManager().registerEvents(this, this.hubPlugin);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        if (this.hubPlugin.getConfiguration()
                .isWhiteListStatus() && !this.hubPlugin.getConfiguration().whiteListByPassList.contains(player.getName()))
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, MessageHelper.colored(this.hubPlugin.getConfiguration().whiteListDefaultReasonMessage
                    .replace("{N}", "\n")));

        if (Bukkit.getOnlinePlayers().size() >= this.hubPlugin.getConfiguration().defaultSlotsOnTheServer) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, MessageHelper.colored(this.hubPlugin.getConfiguration().serverIsFullMessage
                    .replace("{N}", "\n")));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(null);

        IntStream.range(0, 100).forEachOrdered(playerChatClear -> player.sendMessage(" "));
        Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));

        final String messagesJoin = this.hubPlugin.getConfiguration().joinMessages.stream()
                .map(s -> s.replace("{PLAYER}", player.getName()))
                .collect(Collectors.joining("\n"));
        MessageHelper.createMessage(messagesJoin).sendMessage(player);

        player.teleport(Bukkit.getWorld("world_the_end").getSpawnLocation());
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setFoodLevel(20);
        player.setHealth(20);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerAchievementAwarded(final PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        final Player player = (Player) event.getDamager();
        if (player == null || !(event.getEntity() instanceof Player)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
