package fun.ravia.common;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("[ravia-common]: Sucessfly loaded commons.");
    }

}
