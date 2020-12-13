package fun.ravia.common.helper.item;

import fun.ravia.common.helper.message.MessageHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class ItemBuilderHelper {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilderHelper(final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilderHelper(final Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilderHelper(final Material material, final int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilderHelper setName(final String displayName) {
        this.itemMeta.setDisplayName(MessageHelper.colored(displayName));
        return this;
    }

    public ItemBuilderHelper setLore(final List<String> lore) {
        this.itemMeta.setLore(MessageHelper.colored(lore));
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}
