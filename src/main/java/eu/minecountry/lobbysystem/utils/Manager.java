package eu.minecountry.lobbysystem.utils;

import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import eu.minecountry.lobbysystem.utils.intefaces.implementations.LobbyPlayerImpl;
import eu.minecountry.lobbysystem.utils.manager.LocationsManager;
import eu.minecountry.lobbysystem.utils.manager.ServerSettings;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<LobbyPlayer> players = new ArrayList<>();

    public LobbyPlayer getLobbyPlayer(Player player){

        for (LobbyPlayer lobbyPlayer : players) {
            if(lobbyPlayer.getBukkitPlayer().equals(player))
                return lobbyPlayer;
        }

        LobbyPlayer lobbyPlayer = new LobbyPlayerImpl(player);
        players.add(lobbyPlayer);

        return lobbyPlayer;
    }

    public LocationsManager getLocationsManager(){
        return new LocationsManager();
    }

    public ServerSettings getServerSettings(){
        return new ServerSettings();
    }

}
