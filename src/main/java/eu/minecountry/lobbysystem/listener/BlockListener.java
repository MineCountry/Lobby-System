package eu.minecountry.lobbysystem.listener;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        if(!lobbyPlayer.isBuilding()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        if(!lobbyPlayer.isBuilding()){
            event.setCancelled(true);
        }
    }

}
