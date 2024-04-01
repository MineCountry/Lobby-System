package eu.minecountry.lobbysystem.listener.lobbyitems;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.enums.LobbyItems;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Teleporter implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        if(event.getItem() == null)return;

        if(event.getItem().equals(LobbyItems.TELEPORTER.itemstack())){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                lobbyPlayer.openInventory().teleporter();
            }
        }

    }

}
