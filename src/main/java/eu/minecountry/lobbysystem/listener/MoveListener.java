package eu.minecountry.lobbysystem.listener;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        if(manager.getServerSettings().isRangetpEnabled()){
            String locname = manager.getServerSettings().getRangetpLocationname();
            Location location = manager.getLocationsManager().get(locname);

            if(location != null){

                if(player.getLocation().distance(location) >= manager.getServerSettings().getRangetp()){
                    lobbyPlayer.toSpawn();
                }

            }

        }

    }

}
