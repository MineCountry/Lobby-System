package eu.minecountry.lobbysystem.listener;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.enums.LobbyItems;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ProtectItems implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        if(!lobbyPlayer.isBuilding()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player))return;
        Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().equals(LobbyItems.TELEPORTER.itemstack())){
            event.setCancelled(true);
        }

    }

}
