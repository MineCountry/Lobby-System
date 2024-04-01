package eu.minecountry.lobbysystem.utils.intefaces.implementations;

import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.intefaces.InventoryList;
import eu.minecountry.lobbysystem.utils.intefaces.ItemsList;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LobbyPlayerImpl implements LobbyPlayer {

    private Player player;
    private boolean building;

    public LobbyPlayerImpl(Player player) {
        this.player = player;
        this.building = false;
    }

    @Override
    public Player getBukkitPlayer() {
        return player;
    }

    @Override
    public ItemsList updateItems() {
        return new ItemsListImpl(this);
    }

    @Override
    public InventoryList openInventory() {
        return new InventoryListImpl(this);
    }

    public void sendCustom(String message) {
        player.sendMessage(LobbySystem.getPrefix().append(MiniMessage.miniMessage().deserialize(message)));
    }

    @Override
    public void sendSuccess(String message) {
        player.sendMessage(LobbySystem.getPrefix().append(MiniMessage.miniMessage().deserialize("<green>"+message+"</green>")));
    }

    @Override
    public void sendWarning(String message) {
        player.sendMessage(LobbySystem.getPrefix().append(MiniMessage.miniMessage().deserialize("<red>"+message+"</red>")));
    }

    @Override
    public void sendError(String message) {
        player.sendMessage(LobbySystem.getPrefix().append(MiniMessage.miniMessage().deserialize("<dark_red>"+message+"</dark_red>")));
    }

    public void toSpawn() {
        Location spawn = LobbySystem.getInstance().getManager().getLocationsManager().get("spawn");
        if(spawn==null){
            sendCustom("Der Spawn ist noch nicht gesetzt!");
            return;
        }
        player.teleport(spawn);
    }

    public boolean isBuilding() {
        return building;
    }

    public void toggleBuilding() {
        building = !building;
    }

    public void setBuilding(boolean value) {
        this.building = value;
    }
}
