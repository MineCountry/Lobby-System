package eu.minecountry.lobbysystem.utils.intefaces.implementations;

import eu.minecountry.lobbysystem.utils.enums.GUI;
import eu.minecountry.lobbysystem.utils.enums.gui.TeleporterGUI;
import eu.minecountry.lobbysystem.utils.intefaces.InventoryList;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryListImpl implements InventoryList {

    LobbyPlayer lobbyPlayer;

    public InventoryListImpl(LobbyPlayer lobbyPlayer) {
        this.lobbyPlayer = lobbyPlayer;
    }

    @Override
    public void teleporter() {
        Inventory inventory = Bukkit.createInventory(null, GUI.TELEPORT.inventorysize(), GUI.TELEPORT.title());

        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, TeleporterGUI.PLACEHOLDER.itemstack());
        }

        for (TeleporterGUI all : TeleporterGUI.allEnums) {
            if(all.inventoryslot()!=-1){
                if(all.permission()==null){
                    inventory.setItem(all.inventoryslot(), all.itemstack());
                }else {
                    if(lobbyPlayer.getBukkitPlayer().hasPermission(all.permission())){
                        inventory.setItem(all.inventoryslot(), all.itemstack());
                    }
                }
            }
        }

        lobbyPlayer.getBukkitPlayer().openInventory(inventory);
    }
}
