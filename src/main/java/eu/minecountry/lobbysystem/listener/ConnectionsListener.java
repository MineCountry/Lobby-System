package eu.minecountry.lobbysystem.listener;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import eu.minecountry.lobbysystem.utils.manager.ServerSettings;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionsListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);
        ServerSettings settings = manager.getServerSettings();

        if(!settings.isJoinmessageEnabled()) {
            event.joinMessage(null);
        }else {
            Component message = settings.getJoinmessage();
            TextComponent msg = (TextComponent) message;

            if(!msg.content().equals("default")){
                String joinmessage = msg.content();
                joinmessage = joinmessage.replace("%player%", player.getName());

                event.joinMessage(MiniMessage.miniMessage().deserialize(joinmessage));
            }
        }

        lobbyPlayer.toSpawn();
        lobbyPlayer.updateItems().lobby();
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setExp(0);
        player.setGameMode(manager.getServerSettings().getDefaultGamemode() != null ? manager.getServerSettings().getDefaultGamemode() : GameMode.SURVIVAL);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);
        ServerSettings settings = manager.getServerSettings();

        if(!settings.isJoinmessageEnabled()) {
            event.quitMessage(null);
        }else {
            Component message = settings.getQuitmessage();
            TextComponent msg = (TextComponent) message;

            if(!msg.content().equals("default")){
                String quitmessage = msg.content();
                quitmessage = quitmessage.replace("%player%", player.getName());

                event.quitMessage(MiniMessage.miniMessage().deserialize(quitmessage));
            }
        }
    }

}
