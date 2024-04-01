package eu.minecountry.lobbysystem.listener.guis;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.enums.GUI;
import eu.minecountry.lobbysystem.utils.enums.LobbyItems;
import eu.minecountry.lobbysystem.utils.enums.gui.TeleporterGUI;
import eu.minecountry.lobbysystem.utils.manager.LocationsManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Teleporter implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player player))return;
        if(event.getCurrentItem() == null)return;
        LocationsManager locationsManager = LobbySystem.getInstance().getManager().getLocationsManager();

        if(event.getView().title().equals(GUI.TELEPORT.title())){
            event.setCancelled(true);

            for (TeleporterGUI all : TeleporterGUI.allEnums) {
                if(event.getCurrentItem().equals(all.itemstack())){

                    if(all.locationname() != null){

                        if(!all.locationname().startsWith("server-")){

                            if(locationsManager.exists(all.locationname())){
                                Location location = locationsManager.get(all.locationname());

                                player.teleport(location);
                                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                            }

                        }else {
                            String servername = all.locationname().replace("server-", "");

                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            out.writeUTF("BauServer-1");

                            player.sendPluginMessage(LobbySystem.getInstance(), "BungeeCord", out.toByteArray());

                            //Verbinde zum Serer
                        }

                    }

                }
            }

        }

    }

}
