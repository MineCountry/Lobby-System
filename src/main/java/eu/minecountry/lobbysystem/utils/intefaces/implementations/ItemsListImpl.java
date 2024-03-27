package eu.minecountry.lobbysystem.utils.intefaces.implementations;

import eu.minecountry.lobbysystem.utils.enums.LobbyItems;
import eu.minecountry.lobbysystem.utils.intefaces.ItemsList;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsListImpl implements ItemsList {

    LobbyPlayer lobbyPlayer;

    public ItemsListImpl(LobbyPlayer lobbyPlayer) {
        this.lobbyPlayer = lobbyPlayer;
    }

    @Override
    public void lobby() {
        Player player = lobbyPlayer.getBukkitPlayer();

        player.getInventory().clear();

        player.getInventory().setItem(LobbyItems.TELEPORTER.inventoryslot(), LobbyItems.TELEPORTER.itemstack());
    }
}
