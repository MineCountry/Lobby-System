package eu.minecountry.lobbysystem.utils.enums.gui;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.enums.GameDescriptions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public enum TeleporterGUI {

    PLACEHOLDER(Component.empty(), -1, Material.GRAY_STAINED_GLASS_PANE, null, null, null),
    SPAWN(MiniMessage.miniMessage().deserialize("<color:#75ff36>Spawn</color>"), 31, Material.SLIME_BALL, "spawn", GameDescriptions.SPAWN.description(), null),
    SOON_1(MiniMessage.miniMessage().deserialize("<b><red>Coming Soon...</red></b>"), 10, Material.FIRE_CHARGE, null, null, null),
    SOON_2(MiniMessage.miniMessage().deserialize("<b><red>Coming Soon...</red></b>"), 16, Material.FIRE_CHARGE, null, null, null),
    BauServer(MiniMessage.miniMessage().deserialize("<i><color:#ff8936>Verbinde dich mit dem BauServer</color></i>"), 44, Material.LEATHER_HELMET, "server-BauServer-1", null, "lobbysystem.bauserveraccess"),
    CITYBUILD(MiniMessage.miniMessage().deserialize("<b><color:#fff312>CityBuild</color></b>"), 13, Material.GRASS_BLOCK, "citybuild", GameDescriptions.CITYBUILD.description(), null);

    private Component displayname;
    private int slot;
    private Material material;
    private String locationname;
    private Component description;
    private String permission;

    TeleporterGUI(Component displayname, int slot, Material material, String locationname, Component description, String permission) {
        this.displayname = displayname;
        this.slot = slot;
        this.material = material;
        this.locationname = locationname;
        this.description = description;
        this.permission = permission;
    }

    private Component displayname() {
        return displayname;
    }

    public int inventoryslot() {
        return slot;
    }

    private Material material() {
        return material;
    }

    public String permission() {
        return permission;
    }

    public ItemStack itemstack(){
        Manager manager = LobbySystem.getInstance().getManager();
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayname);

        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        if(locationname!=null) {
            if (!locationname.startsWith("server-")) {
                if (!manager.getLocationsManager().exists(locationname)) {
                    meta.lore(Arrays.asList(Component.empty(), MiniMessage.miniMessage().deserialize("<red>Location nicht gesetzt</red>"), Component.empty()));
                } else {
                    if (description != null) {
                        meta.lore(Arrays.asList(Component.empty(), description, Component.empty()));
                    }
                }
            }
        }

        item.setItemMeta(meta);

        return item;
    }

    public String locationname() {
        return locationname;
    }

    public static TeleporterGUI[] allEnums = TeleporterGUI.class.getEnumConstants();
}
