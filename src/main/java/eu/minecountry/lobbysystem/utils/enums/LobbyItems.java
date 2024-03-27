package eu.minecountry.lobbysystem.utils.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum LobbyItems {

    TELEPORTER(MiniMessage.miniMessage().deserialize("<b><color:#9dff00>Teleporter</color></b>"), 4, Material.NETHER_STAR);


    Component displayname;
    int slot;
    Material material;

    LobbyItems(Component displayname, int slot, Material material) {
        this.displayname = displayname;
        this.slot = slot;
        this.material = material;
    }

    public Component displayname() {
        return displayname;
    }

    public int inventoryslot() {
        return slot;
    }

    public Material material() {
        return material;
    }

    public ItemStack itemstack(){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayname);

        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        return item;
    }

}
