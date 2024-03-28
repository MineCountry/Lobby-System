package eu.minecountry.lobbysystem.utils.intefaces;

import org.bukkit.entity.Player;

public interface LobbyPlayer {

    Player getBukkitPlayer();
    ItemsList updateItems();
    InventoryList openInventory();
    void sendCustom(String message);
    void sendWarning(String message);
    void sendError(String message);
    void sendSuccess(String message);
    void toSpawn();
    void toggleBuilding();
    void setBuilding(boolean value);
    boolean isBuilding();

}
