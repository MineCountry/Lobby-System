package eu.minecountry.lobbysystem.utils.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public enum GUI {

    TELEPORT(MiniMessage.miniMessage().deserialize("<b><color:#9dff00>Teleporter</color></b>"), 9*5);

    Component title;
    int inventorysize;

    GUI(Component title, int inventorysize) {
        this.title = title;
        this.inventorysize = inventorysize;
    }

    public Component title() {
        return title;
    }

    public Integer inventorysize() {
        return inventorysize;
    }
}
