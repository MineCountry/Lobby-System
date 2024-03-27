package eu.minecountry.lobbysystem.utils.manager;

import eu.minecountry.lobbysystem.LobbySystem;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationsManager {

    private final FileConfiguration configuration = LobbySystem.getInstance().getConfig();
    String path = configuration.getString("locationspath");

    public LocationsManager() {
        if(path==null){
            path = "Location.";
        }
    }

    public boolean exists(String key){
        key = key.toLowerCase();
        return configuration.getLocation(path+key)!=null;
    }
    public Location get(String key){
        key = key.toLowerCase();
        return configuration.getLocation(path+key);
    }
    public void insert(String key, Location location){
        key = key.toLowerCase();
        configuration.set(path+key, location);
        LobbySystem.getInstance().saveConfig();
    }

    public void delete(String key){
        key = key.toLowerCase();
        configuration.set(path+key, null);
        LobbySystem.getInstance().saveConfig();
    }




}
